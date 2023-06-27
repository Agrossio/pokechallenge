package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemon;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonDetails;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonResults;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class PokemonServiceImpl implements PokemonService {


    private RestTemplate httpClient = new RestTemplate();

    @Override
    public List<MyPokemonListResponse> findAll(String offset, String limit) {

        String requestUrl = "https://pokeapi.co/api/v2/pokemon?offset=" + offset + "&limit=" + limit;

        Optional<ResponseEntity<ApiPokemonListResponse>> apiPokemonListResponse = Optional.of(httpClient.exchange(requestUrl, HttpMethod.GET, null, ApiPokemonListResponse.class));

        apiPokemonListResponse.orElseThrow();


        Optional<ApiPokemonListResponse> apiPokemonListBody = Optional.of(apiPokemonListResponse.get().getBody());

        apiPokemonListBody.orElseThrow();

        List<ApiPokemonResults> apiPokemonResults = apiPokemonListBody.get().getResults();

        List<ApiPokemon> apiPokemonList = new ArrayList<>();

        apiPokemonResults.stream()
                .forEach(pokemonNameUrl -> {

                    ApiPokemon apiPokemon = detailsHttpRequest(pokemonNameUrl.getName(), false);

                    apiPokemonList.add(apiPokemon);
                });


        System.out.println("LISTA " + apiPokemonList);

         return MyPokemonListResponse.toResponse(apiPokemonList);
    }



    @Override
    public MyPokemonResponse getDetails(String name, String language) {
        ApiPokemon apiPokemonDetails = detailsHttpRequest(name, true);

        List<String> apiPokemonDescriptionNames = getDescriptionHttp(name, language);

        apiPokemonDetails.setDescriptions(apiPokemonDescriptionNames);

        return MyPokemonResponse.toResponse(apiPokemonDetails);
    }

    public List<String> getDescriptionHttp(String pokemonName, String language){
        String descriptionUrl = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonName;

        ResponseEntity<ApiPokemonDetails> apiPokemonDescriptionResponse = Optional.of(httpClient.exchange(descriptionUrl, HttpMethod.GET, null, ApiPokemonDetails.class)).orElseThrow();

        ApiPokemonDetails apiPokemonDescription = Optional.of(apiPokemonDescriptionResponse.getBody()).orElseThrow();

        List<String> apiFilteredDescriptionNames = apiPokemonDescription.getDescriptions().stream()
                .filter(apiDescription -> apiDescription.getLanguageName().equals(language))
                .map(apiDescription -> apiDescription.getDescriptionText())
                .toList();

        return apiFilteredDescriptionNames;

    }

    public ApiPokemon detailsHttpRequest(String pokemonName, boolean getMoves){

        String detailsUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;

        ResponseEntity<ApiPokemonDetails> apiPokemonDetailsResponse = Optional.of(httpClient.exchange(detailsUrl, HttpMethod.GET, null, ApiPokemonDetails.class)).orElseThrow();

        ApiPokemonDetails apiPokemonDetails = Optional.of(apiPokemonDetailsResponse.getBody()).orElseThrow();

        // System.out.println("DETAILS " + apiPokemonDetails);

        ApiPokemon apiPokemon = new ApiPokemon();

        // Name
        apiPokemon.setName(apiPokemonDetails.getName());

        // Photo
        apiPokemon.setImageUrl(apiPokemonDetails.getSprites().getOther().getDream_world().getFront_default());

        // Types
        List<String> typeNames = apiPokemonDetails.getTypes().stream()
                .map(apiType -> apiType.getType().get("name"))
                .collect(Collectors.toList());

        apiPokemon.setTypes(typeNames);

        // Weight
        apiPokemon.setWeight(apiPokemonDetails.getWeight());

        // Abilities
        List<String> abilitiesNames = apiPokemonDetails.getAbilities().stream()
                .map(apiAbility -> apiAbility.getAbility().get("name"))
                .collect(Collectors.toList());

        apiPokemon.setAbilities(abilitiesNames);

        // Moves
        if (getMoves == true) {
            List<String> movesNames = apiPokemonDetails.getMoves().stream()
                    .map(apiMove -> apiMove.getMove().get("name"))
                    .collect(Collectors.toList());

            apiPokemon.setMoves(movesNames);
        }

        return apiPokemon;
    }

}
