package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.model.entity.Pokemon;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonDetails;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonResults;
import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PokemonServiceImpl implements PokemonService {

    private String requestUrl = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=2";
    private RestTemplate httpClient = new RestTemplate();

    @Override
    public MyPokemonResponse findAll() {

        ApiPokemonListResponse apiPokemonListResponse = this.httpClient.exchange(this.requestUrl, HttpMethod.GET, null, ApiPokemonListResponse.class).getBody();

        List<ApiPokemonResults> apiPokemonResults = apiPokemonListResponse.getResults();

        List<Pokemon> pokemonEntityList = new ArrayList<>();

        apiPokemonResults.forEach(pokemonNameUrl ->{

            ApiPokemonDetails apiPokemonDetails = this.httpClient.exchange(pokemonNameUrl.getUrl(), HttpMethod.GET, null, ApiPokemonDetails.class).getBody();


            Pokemon pokemonEntity = new Pokemon();

            pokemonEntity.setName(pokemonNameUrl.getName());
            pokemonEntity.setImageUrl(apiPokemonDetails.getSprites().getOther().getDream_world().getFront_default());



            pokemonEntity.setWeight(apiPokemonDetails.getWeight());

            pokemonEntityList.add(pokemonEntity);
//            System.out.println(pokemonNameUrl);
        });

//        for (ApiPokemonResults pokemonNameUrl : apiPokemonResults ) {
//
//            Pokemon pokemonEntity = new Pokemon();
//
//            pokemonEntity.setName(pokemonNameUrl.getName());
//            pokemonEntityList.add(pokemonEntity);
//
//            System.out.println(pokemonNameUrl);
//
//        }


        System.out.println("LISTA" + pokemonEntityList);
//        System.out.println(apiPokemonResults);
//        System.out.println(apiPokemonListResponse);

         return null;
    }
}
