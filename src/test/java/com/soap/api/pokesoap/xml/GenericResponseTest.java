package com.soap.api.pokesoap.xml;

import com.soap.api.pokesoap.xml.pokemon.GenericResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenericResponseTest {

    private GenericResponse genericResponse;

    @BeforeEach
    void setUp() {
        genericResponse = new GenericResponse();
    }

    @Test
    void testSetAndGetStatus() {
        String status = "200 OK";
        genericResponse.setStatus(status);
        assertNotNull(genericResponse.getStatus());
        assertEquals(status, genericResponse.getStatus());
    }

    @Test
    void testSetAndGetMessage() {
        String message = "Success";
        genericResponse.setMessage(message);
        assertNotNull(genericResponse.getMessage());
        assertEquals(message, genericResponse.getMessage());
    }
}