package com.soap.api.pokesoap.external.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Platinum{
    private String back_default;
    private Object back_female;
    private String back_shiny;
    private Object back_shiny_female;
    private String front_default;
    private Object front_female;
    private String front_shiny;
    private Object front_shiny_female;
}