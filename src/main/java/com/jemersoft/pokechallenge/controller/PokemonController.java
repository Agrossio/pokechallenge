package com.jemersoft.pokechallenge.controller;

import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myResponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
@Tag(name = "Pokemon Controller", description = "Allowed actions to get Pokemon info")
public class PokemonController {
    private final PokemonService pokeService;

    @GetMapping
    @Operation(summary = "Get all Pokemons", description = "This endpoint lists all the Pokemons. Yo can limit and move through the results setting 'maxResults' and 'offset' query params.")
    public List<MyPokemonListResponse> findAll(@RequestParam(required = false, defaultValue = "0") String offset, @RequestParam(name = "maxResults", required = false, defaultValue = "5") String limit){

        List<MyPokemonListResponse> pokemonListResponse = pokeService.findAll(offset, limit);

        return pokemonListResponse;
    };

    @GetMapping("/{name}")
    @Operation(summary = "Get Pokemon Details", description = "This endpoint gets the details of a given Pokemon")
    public MyPokemonResponse findById(@PathVariable String name, @RequestParam(required = false, defaultValue = "es") String language){

        MyPokemonResponse pokemonResponse = pokeService.getDetails(name, language);

        return pokemonResponse;
    };


}
