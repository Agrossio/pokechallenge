package com.jemersoft.pokechallenge.model.util.description;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ApiDescription {
    @JsonProperty("flavor_text")
    private String descriptionText;

    @JsonProperty("language")
    private Map<String, String> languageDetails;

    @JsonGetter("language")
    public String getLanguageName(){
      return this.languageDetails.get("name");
   }

}
