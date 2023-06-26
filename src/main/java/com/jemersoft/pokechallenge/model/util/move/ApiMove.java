package com.jemersoft.pokechallenge.model.util.move;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ApiMove {
    @JsonProperty("move")
    private Map<String, String> move;
}
