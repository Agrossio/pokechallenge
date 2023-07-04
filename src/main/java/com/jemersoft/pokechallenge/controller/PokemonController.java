package com.jemersoft.pokechallenge.controller;

import com.jemersoft.pokechallenge.config.Cache;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonListResponse;
import com.jemersoft.pokechallenge.model.response.myresponse.MyPokemonResponse;
import com.jemersoft.pokechallenge.service.abs.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
@Tag(name = "Pokemon Controller", description = "Allowed actions to get Pokemon info")
public class PokemonController {
    private final PokemonService pokeService;

    @GetMapping
    @Operation(summary = "Get all Pokemon's", description = "This endpoint lists all the Pokemon's. Yo can optionally limit and move through the results setting 'maxResults' and 'offset' query params. Default values are: offset=0 and maxResults=5.")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<MyPokemonListResponse> findAll(@RequestParam(required = false, defaultValue = "0") String offset, @RequestParam(name = "maxResults", required = false, defaultValue = "5") String limit){

        List<String> queryParameters = List.of(offset, limit);

        if (Cache.getQueryParametersList() != null && Cache.getQueryParametersList().equals(queryParameters)){
            return Cache.getCachedListResponse();
        }

        Cache.setQueryParametersList(queryParameters);

        return pokeService.findAll(offset, limit);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get Pokemon Details", description = "This endpoint gets the details of a given Pokemon name or id. You can optionally specify a desired language with 'language' query param (default: language=es). It should be the two-letter code of the country where this language is spoken.")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MyPokemonResponse findById(@PathVariable String name, @RequestParam(required = false, defaultValue = "es") String language){

        List<String> queryParameters = List.of(name, language);

        if (Cache.getQueryParameters() != null && Cache.getQueryParameters().equals(queryParameters)){
            return Cache.getCachedResponse();
        }

        Cache.setQueryParameters(queryParameters);

        return pokeService.getDetails(name, language);
    }

}
