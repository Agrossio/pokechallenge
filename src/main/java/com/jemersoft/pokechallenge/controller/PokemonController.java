package com.jemersoft.pokechallenge.controller;

import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokeService;

    @GetMapping
    public List<MyPokemonListResponse> findAll(){

        List<MyPokemonListResponse> pokemonListResponse = pokeService.findAll();

        return pokemonListResponse;
    };

    @GetMapping("/{name}/{language}")
    public MyPokemonResponse findById(@PathVariable String name, @PathVariable String language){

        MyPokemonResponse pokemonResponse = pokeService.getDetails(name, language);

        // System.out.println("DETALLE CONTROLLER " + pokemonResponse);

        return pokemonResponse;
    };


}
