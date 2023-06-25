package com.jemersoft.pokechallenge.model.response.ApiResponse;

import com.jemersoft.pokechallenge.model.entity.Ability;
import com.jemersoft.pokechallenge.model.entity.Move;
import com.jemersoft.pokechallenge.model.entity.Type;
import com.jemersoft.pokechallenge.model.response.ApiResponse.image.ApiSprite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPokemonDetails {
    private ApiSprite sprites;
    private List<Type> types;
    private double weight;
    private List<Ability> abilities;
    private List<Move> moves;

}
