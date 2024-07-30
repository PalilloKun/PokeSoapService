package com.soap.api.pokesoap.xml;

import com.soap.api.pokesoap.xml.pokemon.GetLocationAreaEncountersRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetLocationAreaEncountersRequestTest {

    private GetLocationAreaEncountersRequest getLocationAreaEncountersRequest;

    @BeforeEach
    void setUp() {
        getLocationAreaEncountersRequest = new GetLocationAreaEncountersRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Jigglypuff";
        getLocationAreaEncountersRequest.setName(name);
        assertNotNull(getLocationAreaEncountersRequest.getName());
        assertEquals(name, getLocationAreaEncountersRequest.getName());
    }
}