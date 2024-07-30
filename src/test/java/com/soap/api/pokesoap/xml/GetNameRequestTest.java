package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GetNameRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetNameRequestTest {

    private GetNameRequest getNameRequest;

    @BeforeEach
    void setUp() {
        getNameRequest = new GetNameRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Eevee";
        getNameRequest.setName(name);
        assertNotNull(getNameRequest.getName());
        assertEquals(name, getNameRequest.getName());
    }
}