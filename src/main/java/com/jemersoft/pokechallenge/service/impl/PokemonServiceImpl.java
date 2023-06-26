package com.jemersoft.pokechallenge.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jemersoft.pokechallenge.model.entity.Pokemon;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemon;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonDetails;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonResults;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.model.util.type.ApiType;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.Data;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class PokemonServiceImpl implements PokemonService {

    private String requestUrl = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20";
    private RestTemplate httpClient = new RestTemplate();

    @Override
    public List<MyPokemonListResponse> findAll() {

        Optional<ResponseEntity<ApiPokemonListResponse>> apiPokemonListResponse = Optional.of(httpClient.exchange(this.requestUrl, HttpMethod.GET, null, ApiPokemonListResponse.class));

        apiPokemonListResponse.orElseThrow();


        Optional<ApiPokemonListResponse> apiPokemonListBody = Optional.of(apiPokemonListResponse.get().getBody());

        apiPokemonListBody.orElseThrow();

        List<ApiPokemonResults> apiPokemonResults = apiPokemonListBody.get().getResults();

        List<ApiPokemon> apiPokemonList = new ArrayList<>();

        apiPokemonResults.stream()
                .forEach(pokemonNameUrl -> {

            ApiPokemonDetails apiPokemonDetails = httpClient.exchange(pokemonNameUrl.getUrl(), HttpMethod.GET, null, ApiPokemonDetails.class).getBody();

            System.out.println("DETAILS " + apiPokemonDetails);

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

            apiPokemonList.add(apiPokemon);
        });


        System.out.println("LISTA" + apiPokemonList);
//        System.out.println(apiPokemonResults);
//        System.out.println(apiPokemonListResponse);

         return MyPokemonListResponse.toResponse(apiPokemonList);
    }

    @Override
    public MyPokemonResponse getDetails(String name) {
        return null;
    }
}
