package com.jemersoft.pokechallenge.model.response.myResponse;

import com.jemersoft.pokechallenge.model.response.ApiResponse.ApiPokemon;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

// Builder con herencia: https://www.baeldung.com/lombok-builder-inheritance
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class MyPokemonResponse extends MyPokemonListResponse {
    private List<String> descriptions;
    private List<String> moves;
    public static MyPokemonResponse toResponse(ApiPokemon apiPokemon){
        return MyPokemonResponse.builder()
                .name(apiPokemon.getName())
                .imageUrl(apiPokemon.getImageUrl())
                .type(apiPokemon.getTypes())
                .weight(apiPokemon.getWeight())
                .abilities(apiPokemon.getAbilities())
                .descriptions(apiPokemon.getDescriptions())
                .moves(apiPokemon.getMoves())
                .build();
    }
}
