package com.jemersoft.pokechallenge.model.util.ability;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ApiAbility {
    @JsonProperty("ability")
    private Map<String, String> ability;
}
