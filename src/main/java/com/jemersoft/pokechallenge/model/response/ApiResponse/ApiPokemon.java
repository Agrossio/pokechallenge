package com.jemersoft.pokechallenge.model.response.ApiResponse;

import com.jemersoft.pokechallenge.model.entity.Ability;
import com.jemersoft.pokechallenge.model.entity.Move;
import com.jemersoft.pokechallenge.model.entity.Type;

import com.jemersoft.pokechallenge.model.util.type.ApiType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiPokemon {
// Pokemon Grid:
    private String name;
    private String imageUrl;
    private List<String> types;
    private double weight;
    private List<String> abilities;

// Pokemon Details:
    private String description;
    private List<Move> moves;

}
