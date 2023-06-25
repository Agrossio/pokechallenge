package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
public class PokemonServiceImpl implements PokemonService {

    private String requestUrl = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=2";
    private RestTemplate httpClient = new RestTemplate(); // = new RestTemplate();

    @Override
    public MyPokemonResponse findAll() {
         ResponseEntity<ApiPokemonListResponse> pokeListResponseEntity = this.httpClient.exchange(this.requestUrl, HttpMethod.GET, null, ApiPokemonListResponse.class);

        ApiPokemonListResponse pokeList = pokeListResponseEntity.getBody();

        for (Object pokemon : pokeList.getResults() ) {

        }

        System.out.println(pokeList);

         return null;
    }
}
