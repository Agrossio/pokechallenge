package com.jemersoft.pokechallenge.model.response.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPokemonResponse {
    Object sprites;
    List<Object> type;
    double weight;
    private List<Object> abilities;
}
