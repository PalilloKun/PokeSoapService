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
public class RedBlue extends CommonProperties {

    @JsonProperty("back_gray")
    private String backGray;
    @JsonProperty("back_transparent")
    private String backTransparent;
    @JsonProperty("front_gray")
    private String frontGray;
    @JsonProperty("front_transparent")
    private String frontTransparent;
}