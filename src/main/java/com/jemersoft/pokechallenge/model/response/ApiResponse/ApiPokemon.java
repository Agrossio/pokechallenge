package com.jemersoft.pokechallenge.model.response.ApiResponse;

import com.jemersoft.pokechallenge.model.entity.Ability;
import com.jemersoft.pokechallenge.model.entity.Move;
import com.jemersoft.pokechallenge.model.entity.Type;

import lombok.Data;

import java.util.List;

@Data
public class ApiPokemon {
    private String name;
    private String imageUrl;
    private List<Type> types;
    private double weight;
    private List<Ability> abilities;
    private String description;
    private List<Move> moves;

}
