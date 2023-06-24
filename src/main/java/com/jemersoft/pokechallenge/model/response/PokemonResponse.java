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
public class PokemonResponse {
    private String basicInfo;
    private String description;
    private List<String> moves;

}
