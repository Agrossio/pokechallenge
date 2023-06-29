package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.exception.customExceptions.BadRequestException;
import com.jemersoft.pokechallenge.exception.customExceptions.NotFoundException;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemon;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonDetails;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonResults;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonListResponseBody;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2";
    private final RestTemplate httpClient = new RestTemplate();

    @Override
    public List<MyPokemonListResponse> findAll(String offset, String limit) throws BadRequestException {

        String requestUrl = BASE_URL + "/pokemon?offset=" + offset + "&limit=" + limit;

        try {
            ResponseEntity<ApiPokemonListResponseBody> apiPokemonListResponse = httpClient.exchange(requestUrl, HttpMethod.GET, null, ApiPokemonListResponseBody.class);

            ApiPokemonListResponseBody apiPokemonListBody = Optional.ofNullable(apiPokemonListResponse.getBody()).orElseThrow(()-> new NullPointerException("Response body is null"));

            List<ApiPokemonResults> apiPokemonResults = apiPokemonListBody.getResults();

            List<ApiPokemon> apiPokemonList = new ArrayList<>();

            apiPokemonResults
                    .forEach(pokemonNameUrl -> {

                        ApiPokemon apiPokemon = detailsHttpRequest(pokemonNameUrl.getName(), false);

                        apiPokemonList.add(apiPokemon);
                    });

             return MyPokemonListResponse.toResponse(apiPokemonList);

        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Bad Request", e);
        }

    }

    @Override
    public MyPokemonResponse getDetails(String name, String language) {
        ApiPokemon apiPokemonDetails = detailsHttpRequest(name, true);

        List<String> apiPokemonDescriptionNames = getDescriptionHttp(name, language);

        apiPokemonDetails.setDescriptions(apiPokemonDescriptionNames);

        return MyPokemonResponse.toResponse(apiPokemonDetails);
    }

    public List<String> getDescriptionHttp(String pokemonName, String language) throws NotFoundException, BadRequestException {
        String descriptionUrl = BASE_URL + "/pokemon-species/" + pokemonName;

        try {

            ResponseEntity<ApiPokemonDetails> apiPokemonDescriptionResponse = httpClient.exchange(descriptionUrl, HttpMethod.GET, null, ApiPokemonDetails.class);

            Optional<ApiPokemonDetails> apiPokemonDescription = Optional.ofNullable(apiPokemonDescriptionResponse.getBody());

            return apiPokemonDescription.orElseThrow(()-> new NullPointerException("Response Body is null")).getDescriptions().stream()
                    .filter(apiDescription -> apiDescription.getLanguageName().equals(language))
                    .map(apiDescription -> {
                        String description = apiDescription.getDescriptionText();
                        return description.replace("\n", " ").replace("\f", " ");
                    })
                    .toList();

        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Pokemon Not Found", e);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Bad Request", e);
        }
    }

    public ApiPokemon detailsHttpRequest(String pokemonName, boolean getMoves) throws NotFoundException, BadRequestException {

        String detailsUrl = BASE_URL + "/pokemon/" + pokemonName;

        try {

        ResponseEntity<ApiPokemonDetails> apiPokemonDetailsResponse = httpClient.exchange(detailsUrl, HttpMethod.GET, null, ApiPokemonDetails.class);

            ApiPokemonDetails apiPokemonDetails = apiPokemonDetailsResponse.getBody();

            ApiPokemon apiPokemon = new ApiPokemon();

            // Name
            apiPokemon.setName(apiPokemonDetails.getName());

            // Photo
            apiPokemon.setImageUrl(apiPokemonDetails.getSprites().getOther().getDream_world().getFront_default());

            // Types
            List<String> typeNames = apiPokemonDetails.getTypes().stream()
                    .map(apiType -> apiType.getType().get("name"))
                    .toList();

            apiPokemon.setTypes(typeNames);

            // Weight
            apiPokemon.setWeight(apiPokemonDetails.getWeight());

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
            throw new NotFoundException("Pokemon Not Found", e);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Bad Request", e);
        }

    }

}
