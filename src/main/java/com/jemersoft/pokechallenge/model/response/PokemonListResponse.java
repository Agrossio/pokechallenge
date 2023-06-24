package com.jemersoft.pokechallenge.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonListResponse {
    String imageUrl;
    String type;
    double weight;
    private List<String> abilities;
}
