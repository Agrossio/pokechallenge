package com.jemersoft.pokechallenge.model.util.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ApiType {
   @JsonProperty("type")
private Map<String, String> type;

//   @JsonGetter("type")
//   public String getName(){
//      return this.type.get("name");
//   }

}
