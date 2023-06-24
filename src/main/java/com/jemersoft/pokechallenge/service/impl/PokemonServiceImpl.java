package com.jemersoft.pokechallenge.service.impl;

import com.jemersoft.pokechallenge.model.response.PokemonResponse;
import com.jemersoft.pokechallenge.service.interf.PokemonService;
import org.springframework.web.client.RestTemplate;

public class PokemonServiceImpl implements PokemonService {

    private RestTemplate httpCLient = new RestTemplate();
    @Override
    public PokemonResponse findAll() {
        return null;
    }
}
