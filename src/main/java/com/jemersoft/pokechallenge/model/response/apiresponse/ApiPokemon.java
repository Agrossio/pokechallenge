package com.jemersoft.pokechallenge.model.response.apiresponse;

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
    private List<String> descriptions;
    private List<String> moves;

}
