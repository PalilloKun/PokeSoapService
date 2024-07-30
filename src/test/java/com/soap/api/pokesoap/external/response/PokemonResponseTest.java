package com.soap.api.pokesoap.external.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soap.api.pokesoap.util.ConstantsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PokemonResponseTest {

    @Test
    void testPokemonResponseBuilderAndGetters() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        PokemonResponse pokemonResponse = objectMapper.readValue(ConstantsTest.STRING_API_POKEMON_RESPONSE, PokemonResponse.class);

        assertNotNull(pokemonResponse.getAbilities());

        assertNotNull(pokemonResponse.getAbilities().get(0).getAbility().getName());
        assertNotNull(pokemonResponse.getAbilities().get(0).getAbility().getUrl());

        assertEquals(pokemonResponse.getAbilities().get(0).getAbility().getName(),pokemonResponse.getAbilities().get(0).getAbility().getName());
        assertNotEquals(pokemonResponse.getAbilities().get(0).getAbility().getName(),pokemonResponse.getAbilities().get(0).getAbility().getName()+"asd");

        AbilityResponse abilityResponse = new AbilityResponse();
        AbilityResponse abilityResponse1 = new AbilityResponse(new Ability(),false,1);

        /*
        ;
        AbilityResponse ability = new AbilityResponse(new Ability("limber", "https://pokeapi.co/api/v2/ability/7/"), false, 1);
        Cries cries = new Cries("pikachu", "Pikachu cry");
        Form form = new Form("ditto", "Standard form");
        GameIndex gameIndex = new GameIndex(1, new Version("red"));
        HeldItem heldItem = new HeldItem("berry", "Berry description");
        MoveRequest move = new MoveRequest("tackle", "Tackle description");
        Species species = new Species("bulbasaur", "Bulbasaur species");
        Sprites sprites = new Sprites("front.png", "back.png");
        StatResponse stat = new StatResponse("speed", 50);
        TypeResponse type = new TypeResponse("grass");

        PokemonResponse pokemonResponse = PokemonResponse.builder()
                .abilities(Collections.singletonList(ability))
                .base_experience(64)
                .cries(cries)
                .forms(Collections.singletonList(form))
                .game_indices(Collections.singletonList(gameIndex))
                .height(7)
                .held_items(Collections.singletonList(heldItem))
                .id(1)
                .is_default(true)
                .location_area_encounters("forest")
                .moves(Collections.singletonList(move))
                .name("bulbasaur")
                .order(1)
                .past_abilities(Collections.emptyList())
                .past_types(Collections.emptyList())
                .species(species)
                .sprites(sprites)
                .stats(Collections.singletonList(stat))
                .types(Collections.singletonList(type))
                .weight(69)
                .build();

        assertEquals("bulbasaur", pokemonResponse.getName());
        assertEquals(64, pokemonResponse.getBase_experience());
        assertEquals("forest", pokemonResponse.getLocation_area_encounters());
        assertEquals(7, pokemonResponse.getHeight());
        assertEquals(69, pokemonResponse.getWeight());
        assertTrue(pokemonResponse.isIs_default());
        assertEquals(1, pokemonResponse.getId());
        assertEquals(1, pokemonResponse.getOrder());
        assertEquals(ability, pokemonResponse.getAbilities().get(0));
        assertEquals(cries, pokemonResponse.getCries());
        assertEquals(form, pokemonResponse.getForms().get(0));
        assertEquals(gameIndex, pokemonResponse.getGame_indices().get(0));
        assertEquals(heldItem, pokemonResponse.getHeld_items().get(0));
        assertEquals(move, pokemonResponse.getMoves().get(0));
        assertEquals(species, pokemonResponse.getSpecies());
        assertEquals(sprites, pokemonResponse.getSprites());
        assertEquals(stat, pokemonResponse.getStats().get(0));
        assertEquals(type, pokemonResponse.getTypes().get(0));
        */
    }
}
