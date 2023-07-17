package com.jemersoft.pokechallenge.controller;

import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPagedResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
@Tag(name = "Pokemon Controller", description = "Allowed actions to get Pokemon info")
public class PokemonController {
    private final PokemonService pokeService;

    @GetMapping
    @Operation(summary = "Get all Pokemon's", description = "This endpoint lists all the Pokemon's. You can optionally limit and move through the results setting 'maxResults' and 'offset' query params. To avoid getting cached results you can add 'cached=false' option. Default values are: offset=0, maxResults=5 and cached=true.")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MyPagedResponse findAll(@RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(name = "maxResults", required = false, defaultValue = "5") Integer limit, @RequestParam(required = false, defaultValue = "true") boolean cached){
        return pokeService.findAll(offset, limit, cached);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get Pokemon Details", description = "This endpoint gets the details of a given Pokemon name or id. You can optionally specify a desired language with 'language' query param. It should be the two-letter code of the country where this language is spoken. To avoid getting cached results you can add 'cached=false' option (Default values are: language=es and cached=true)")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MyPagedResponse findById(@PathVariable String name, @RequestParam(required = false, defaultValue = "es") String language, @RequestParam(required = false, defaultValue = "true") boolean cached){
        return pokeService.getDetails(name, language, cached);
    }

}
