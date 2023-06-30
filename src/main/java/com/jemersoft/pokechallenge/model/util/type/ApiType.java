package com.jemersoft.pokechallenge.model.util.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiType {
   @JsonProperty("type")
   private Map<String, String> type;

}
