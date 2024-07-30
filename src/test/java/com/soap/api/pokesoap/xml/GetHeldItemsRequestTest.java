package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GetHeldItemsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetHeldItemsRequestTest {

    private GetHeldItemsRequest getHeldItemsRequest;

    @BeforeEach
    void setUp() {
        getHeldItemsRequest = new GetHeldItemsRequest();
    }

    @Test
    void testSetAndGetName() {
        String name = "Bulbasaur";
        getHeldItemsRequest.setName(name);
        assertNotNull(getHeldItemsRequest.getName());
        assertEquals(name, getHeldItemsRequest.getName());
    }
}