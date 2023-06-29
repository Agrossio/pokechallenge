package com.jemersoft.pokechallenge.model.util.move;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiMove {
    @JsonProperty("move")
    private Map<String, String> move;
}
