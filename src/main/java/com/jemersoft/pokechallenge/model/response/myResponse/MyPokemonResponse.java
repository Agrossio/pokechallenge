package com.jemersoft.pokechallenge.model.response.myResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPokemonResponse extends MyPokemonListResponse {
    private String basicInfo;
    private String description;
    private List<String> moves;

}
