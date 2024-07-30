package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GetAbilityRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAbilityRequestTest {

    private GetAbilityRequest getAbilityRequest;

    @BeforeEach
    void setUp() {
        getAbilityRequest = new GetAbilityRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Pikachu";
        getAbilityRequest.setName(name);
        assertNotNull(getAbilityRequest.getName());
        assertEquals(name, getAbilityRequest.getName());
    }
}