package com.jemersoft.pokechallenge.model.response.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jemersoft.pokechallenge.model.util.ability.ApiAbility;
import com.jemersoft.pokechallenge.model.util.description.ApiDescription;
import com.jemersoft.pokechallenge.model.util.image.ApiSprite;

import com.jemersoft.pokechallenge.model.util.move.ApiMove;
import com.jemersoft.pokechallenge.model.util.type.ApiType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiPokemonDetails implements Serializable {
    private String name;
    private ApiSprite sprites;
    private List<ApiType> types;
    private double weight;
    private List<ApiAbility> abilities;
    @JsonProperty("flavor_text_entries")
    private List<ApiDescription> descriptions;
    private List<ApiMove> moves;



}
