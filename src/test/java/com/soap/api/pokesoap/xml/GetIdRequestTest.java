package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GetIdRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetIdRequestTest {

    private GetIdRequest getIdRequest;

    @BeforeEach
    void setUp() {
        getIdRequest = new GetIdRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Squirtle";
        getIdRequest.setName(name);
        assertNotNull(getIdRequest.getName());
        assertEquals(name, getIdRequest.getName());
    }
}