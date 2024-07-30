package com.soap.api.pokesoap.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Versions{

    @JsonProperty("generation_i")
    private GenerationI generationi;
    @JsonProperty("generation_ii")
    private GenerationIi generationii;
    @JsonProperty("generation_iii")
    private GenerationIii generationiii;
    @JsonProperty("generation_iv")
    private GenerationIv generationiv;
    @JsonProperty("generation_v")
    private GenerationV generationv;
    @JsonProperty("generation_vi")
    private GenerationVi generationvi;
    @JsonProperty("generation_vii")
    private GenerationVii generationvii;
    @JsonProperty("generation_viii")
    private GenerationViii generationviii;
}