package com.jemersoft.pokechallenge.model.util.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ApiFlavorTextEntry {
    @JsonProperty("flavor_text")
    private Map<String, String> description;
}
