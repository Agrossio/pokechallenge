package com.jemersoft.pokechallenge.controller;

import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokeService;

    @GetMapping
    public void findAll(){
        pokeService.findAll();
    };


}
