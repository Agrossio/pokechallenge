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
public class ApiPokemonListResponseBody {
    private Integer count;
    private String next;
    private String previous;
    private List<ApiPokemonResults> results;
}
