package com.jemersoft.pokechallenge.model.response.myResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPokemonListResponse {
    String name;
    Object sprites;
    List<Object> type;
    double weight;
    private List<Object> abilities;

}
