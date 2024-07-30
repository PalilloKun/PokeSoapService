package com.soap.api.pokesoap.external.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartgoldSoulsilver extends CommonProperties {

    @JsonProperty("back_female")
    private Object backFemale;
    @JsonProperty("back_shiny_female")
    private Object backShinyFemale;
    @JsonProperty("front_female")
    private Object frontFemale;
    @JsonProperty("front_shiny_female")
    private Object frontShinyFemale;
}
