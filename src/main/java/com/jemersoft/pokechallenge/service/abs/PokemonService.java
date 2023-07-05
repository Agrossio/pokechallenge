package com.jemersoft.pokechallenge.service.abs;

import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPagedResponse;

public interface PokemonService {
    MyPagedResponse findAll(Integer offset, Integer limit, boolean cached);
    MyPokemonResponse getDetails(String name, String language, boolean cached);
}
