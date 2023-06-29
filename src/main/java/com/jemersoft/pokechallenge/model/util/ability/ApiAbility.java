package com.jemersoft.pokechallenge.model.util.ability;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiAbility {
    @JsonProperty("ability")
    private Map<String, String> ability;
}
