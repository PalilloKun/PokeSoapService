package com.soap.api.pokesoap.xml;


import com.soap.api.pokesoap.xml.pokemon.GenericResponse;
import com.soap.api.pokesoap.xml.pokemon.GetAbilityRequest;
import com.soap.api.pokesoap.xml.pokemon.GetBaseExperienceRequest;
import com.soap.api.pokesoap.xml.pokemon.GetHeldItemsRequest;
import com.soap.api.pokesoap.xml.pokemon.GetIdRequest;
import com.soap.api.pokesoap.xml.pokemon.GetLocationAreaEncountersRequest;
import com.soap.api.pokesoap.xml.pokemon.GetNameRequest;
import com.soap.api.pokesoap.xml.pokemon.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectFactoryTest {

    private ObjectFactory objectFactory;

    @BeforeEach
    void setUp() {
        objectFactory = new ObjectFactory();
    }

    @Test
    void testCreateGetAbilityRequest() {
        GetAbilityRequest request = objectFactory.createGetAbilityRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetAbilityRequest);
    }

    @Test
    void testCreateGetBaseExperienceRequest() {
        GetBaseExperienceRequest request = objectFactory.createGetBaseExperienceRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetBaseExperienceRequest);
    }

    @Test
    void testCreateGetHeldItemsRequest() {
        GetHeldItemsRequest request = objectFactory.createGetHeldItemsRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetHeldItemsRequest);
    }

    @Test
    void testCreateGetIdRequest() {
        GetIdRequest request = objectFactory.createGetIdRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetIdRequest);
    }

    @Test
    void testCreateGetNameRequest() {
        GetNameRequest request = objectFactory.createGetNameRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetNameRequest);
    }

    @Test
    void testCreateGetLocationAreaEncountersRequest() {
        GetLocationAreaEncountersRequest request = objectFactory.createGetLocationAreaEncountersRequest();
        assertNotNull(request);
        assertTrue(request instanceof GetLocationAreaEncountersRequest);
    }

    @Test
    void testCreateGenericResponse() {
        GenericResponse response = objectFactory.createGenericResponse();
        assertNotNull(response);
        assertTrue(response instanceof GenericResponse);
    }
}