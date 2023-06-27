package com.jemersoft.pokechallenge.service.abs;

import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;

import java.util.List;

public interface PokemonService {
    List<MyPokemonListResponse> findAll();
    MyPokemonResponse getDetails(String name, String language);
}
