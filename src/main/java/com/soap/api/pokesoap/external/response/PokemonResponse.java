package com.soap.api.pokesoap.external.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {

    private List<AbilityResponse> abilities;

    @JsonProperty("base_experience")
    private int baseExperience;
    private Cries cries;
    private List<Form> forms;
    @JsonProperty("game_indices")
    private List<GameIndex> gameIndices;
    private int height;
    @JsonProperty("held_items")
    private List<HeldItem> heldItems;
    private int id;
    @JsonProperty("is_default")
    private boolean isDefault;
    @JsonProperty("location_area_encounters")
    private String locationAreaEncounters;
    private List<MoveRequest> moves;
    private String name;
    private int order;
    @JsonProperty("past_abilities")
    private List<Object> pastAbilities;
    @JsonProperty("past_types")
    private List<Object> pastTypes;
    private Species species;
    private Sprites sprites;
    private List<StatResponse> stats;
    private List<TypeResponse> types;
    private int weight;
}
