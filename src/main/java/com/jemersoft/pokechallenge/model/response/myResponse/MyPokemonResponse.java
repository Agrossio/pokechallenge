package com.jemersoft.pokechallenge.model.response.myResponse;

import com.jemersoft.pokechallenge.model.entity.Move;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPokemonResponse extends MyPokemonListResponse {
    private String description;
    private List<Move> moves;

}
