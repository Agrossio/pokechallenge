package com.jemersoft.pokechallenge.model.response.ApiResponse;

import com.jemersoft.pokechallenge.model.entity.Ability;
import com.jemersoft.pokechallenge.model.entity.Move;
import com.jemersoft.pokechallenge.model.entity.Type;
import com.jemersoft.pokechallenge.model.util.ability.ApiAbility;
import com.jemersoft.pokechallenge.model.util.image.ApiSprite;

import com.jemersoft.pokechallenge.model.util.type.ApiType;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiPokemonDetails {
    private String name;
    private ApiSprite sprites;
    private List<ApiType> types;
    private double weight;
    private List<ApiAbility> abilities;
    private List<Move> moves;

}
