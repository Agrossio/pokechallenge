package com.jemersoft.pokechallenge.model.response.myResponse;

import com.jemersoft.pokechallenge.model.entity.Ability;
import com.jemersoft.pokechallenge.model.entity.Move;
import com.jemersoft.pokechallenge.model.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class MyPokemonListResponse {
    private String name;
    private String imageUrl;
    private List<Type> type;
    private double weight;
    private List<Ability> abilities;

}
