package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.config.Cache;
import com.jemersoft.pokechallenge.exception.customExceptions.BadRequestException;
import com.jemersoft.pokechallenge.exception.customExceptions.NotFoundException;
import com.jemersoft.pokechallenge.model.response.apiresponse.ApiPokemon;
import com.jemersoft.pokechallenge.model.response.apiresponse.ApiPokemonDetails;
import com.jemersoft.pokechallenge.model.response.apiresponse.ApiPokemonResults;
import com.jemersoft.pokechallenge.model.response.apiresponse.ApiPokemonListResponseBody;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPagedResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    @Value("${pokeapi.base-url}") String BASE_URL;
    private static final String BAD_REQUEST = "Bad Request";
    private static final String POKE_NOT_FOUND = "Pokemon Not Found";
    private final RestTemplate httpClient = new RestTemplate();

    @Override
    public MyPagedResponse findAll(Integer offset, Integer limit, boolean cached) throws BadRequestException {

        try {

            // To get the Host Url:
            ServletRequestAttributes requestAttributes = Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).orElseThrow(()-> new NullPointerException("Request is null"));

            final String HOST_URL = requestAttributes.getRequest().getRequestURI();

            String nextUrl = HOST_URL + "?offset=" + (offset+limit) + "&maxResults=" + limit + "&cached=true";
            String prevUrl = (offset-limit) >= 0 ? HOST_URL + "?offset=" + (offset-limit) + "&maxResults=" + limit + "&cached=true" : null;

            List<Integer> queryParameters = List.of(offset, limit);

            if (cached && Cache.getQueryParametersList() != null && Cache.getQueryParametersList().equals(queryParameters)){

                return MyPagedResponse.builder()
                        .cached(true)
                        .results(Cache.getCachedListResponse())
                        .next(nextUrl)
                        .previous(prevUrl)
                        .count(Cache.getCachedListResponse().size())
                        .build();
            }

        String requestUrl = BASE_URL + "/pokemon?offset=" + offset + "&limit=" + limit;

            ResponseEntity<ApiPokemonListResponseBody> apiPokemonListResponse = httpClient.exchange(requestUrl, HttpMethod.GET, null, ApiPokemonListResponseBody.class);

            ApiPokemonListResponseBody apiPokemonListBody = Optional.ofNullable(apiPokemonListResponse.getBody()).orElseThrow(()-> new NullPointerException("Response body is null"));

            List<ApiPokemonResults> apiPokemonResults = apiPokemonListBody.getResults();

            List<ApiPokemon> apiPokemonList =  apiPokemonResults
                    .stream().map(pokemonNameUrl -> detailsHttpRequest(pokemonNameUrl.getName(), false))
                    .toList();

            List<MyPokemonListResponse> myPokemonListResponseList = MyPokemonListResponse.toResponse(apiPokemonList);

            Cache.setQueryParametersList(queryParameters);
            Cache.setCachedListResponse(myPokemonListResponseList);

            return MyPagedResponse.builder()
                    .next(nextUrl)
                    .previous(prevUrl)
                    .count(myPokemonListResponseList.size())
                    .results(myPokemonListResponseList)
                    .cached(false)
                    .build();

        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException(BAD_REQUEST, e);
        }

    }

    @Override
    public MyPokemonResponse getDetails(String name, String language, boolean cached) {

        List<String> queryParameters = List.of(name, language);

        if (cached && Cache.getQueryParameters() != null && Cache.getQueryParameters().equals(queryParameters)){
            return Cache.getCachedResponse();
        }

        // Fetch Pokemon details:
        ApiPokemon apiPokemonDetails = detailsHttpRequest(name, true);

        // Fetch Pokemon descriptions:
        List<String> apiPokemonDescriptionNames = getDescriptionHttp(name, language);

        // Add descriptions to Pokémon details:
        apiPokemonDetails.setDescriptions(apiPokemonDescriptionNames);

        MyPokemonResponse myPokemonResponse = MyPokemonResponse.toResponse(apiPokemonDetails);

        Cache.setQueryParameters(queryParameters);
        Cache.setCachedResponse(myPokemonResponse);

        return myPokemonResponse;

    }

    public List<String> getDescriptionHttp(String pokemonName, String language) throws NotFoundException, BadRequestException {
        String descriptionUrl = BASE_URL + "/pokemon-species/" + pokemonName;

        try {

            ResponseEntity<ApiPokemonDetails> apiPokemonDescriptionResponse = httpClient.exchange(descriptionUrl, HttpMethod.GET, null, ApiPokemonDetails.class);

            ApiPokemonDetails apiPokemonDescription = Optional.ofNullable(apiPokemonDescriptionResponse.getBody()).orElseThrow(()-> new NullPointerException("Response Body is null"));

            return apiPokemonDescription.getDescriptions().stream()
                    .filter(apiDescription -> apiDescription.getLanguageName().equals(language))
                    .map(apiDescription -> {
                        String description = apiDescription.getDescriptionText();
                        return description.replace("\n", " ").replace("\f", " ");
                    })
                    .toList();

        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException(POKE_NOT_FOUND, e);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException(BAD_REQUEST, e);
        }
    }

    public ApiPokemon detailsHttpRequest(String pokemonName, boolean getMoves) throws NotFoundException, BadRequestException {

        String detailsUrl = BASE_URL + "/pokemon/" + pokemonName;

        try {

        ResponseEntity<ApiPokemonDetails> apiPokemonDetailsResponse = httpClient.exchange(detailsUrl, HttpMethod.GET, null, ApiPokemonDetails.class);

            ApiPokemonDetails apiPokemonDetails = Optional.ofNullable(apiPokemonDetailsResponse.getBody()).orElseThrow(()-> new NullPointerException("Response Body is null"));

            ApiPokemon apiPokemon = new ApiPokemon();

            // Name
            apiPokemon.setName(apiPokemonDetails.getName());

            // Weight
            apiPokemon.setWeight(apiPokemonDetails.getWeight());

            // Photo
            apiPokemon.setImageUrl(apiPokemonDetails.getSprites().getOther().getDream_world().getFront_default());

            // Types
            List<String> typeNames = apiPokemonDetails.getTypes().stream()
                    .map(apiType -> apiType.getType().get("name"))
                    .toList();
            apiPokemon.setTypes(typeNames);

            // Abilities
            List<String> abilitiesNames = apiPokemonDetails.getAbilities().stream()
                    .map(apiAbility -> apiAbility.getAbility().get("name"))
                    .toList();
            apiPokemon.setAbilities(abilitiesNames);

            // Moves
            if (getMoves) {
                List<String> movesNames = apiPokemonDetails.getMoves().stream()
                        .map(apiMove -> apiMove.getMove().get("name"))
                        .toList();
                apiPokemon.setMoves(movesNames);
            }

            return apiPokemon;

        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException(POKE_NOT_FOUND, e);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException(BAD_REQUEST, e);
        }
    }

}
