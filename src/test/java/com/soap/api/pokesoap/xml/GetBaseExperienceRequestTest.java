package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GetBaseExperienceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetBaseExperienceRequestTest {

    private GetBaseExperienceRequest getBaseExperienceRequest;

    @BeforeEach
    void setUp() {
        getBaseExperienceRequest = new GetBaseExperienceRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Charizard";
        getBaseExperienceRequest.setName(name);
        assertNotNull(getBaseExperienceRequest.getName());
        assertEquals(name, getBaseExperienceRequest.getName());
    }
}