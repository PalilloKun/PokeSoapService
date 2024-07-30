package com.soap.api.pokesoap.endpoint;

import com.soap.api.pokesoap.external.client.PokeApiClient;
import com.soap.api.pokesoap.external.response.Ability;
import com.soap.api.pokesoap.external.response.AbilityResponse;
import com.soap.api.pokesoap.external.response.HeldItem;
import com.soap.api.pokesoap.external.response.Item;
import com.soap.api.pokesoap.external.response.PokemonResponse;
import com.soap.api.pokesoap.external.response.VersionDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class PokemonEndPointTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private PokeApiClient pokeApiClient;

    private MockWebServiceClient mockClient;

    @BeforeEach
    void setup() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void testGetAbilitySuccess() {
        // Mock the PokeApiClient response

        Ability ability = new Ability("hidden", "http");
        AbilityResponse abilityResponse = new AbilityResponse();
        abilityResponse.setHidden(false);
        abilityResponse.setSlot(0);
        abilityResponse.setAbility(ability);


        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setAbilities(Arrays.asList(abilityResponse));

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getAbilityRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getAbilityRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[{\"ability\":{\"name\":\"hidden\",\"url\":\"http\"},\"slot\":0,\"is_hidden\":false}]</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }


    @Test
    void testBaseExperienceSuccess() {
        // Mock the PokeApiClient response

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setBaseExperience(1);
        //pokemonResponse.setAbilities(Arrays.asList(abilityResponse));

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getBaseExperienceRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getBaseExperienceRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>1</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }


    @Test
    void testHeldItemsRequestSuccess() {
        // Mock the PokeApiClient response

        VersionDetail versionDetail = new VersionDetail();
        versionDetail.setRarity(2);
        List<VersionDetail> versionDetails = new ArrayList<>();
        versionDetails.add(versionDetail);
        Item item = new Item();
        item.setName("Name");
        item.setUrl("http");
        HeldItem heldItem = new HeldItem();
        heldItem.setItem(item);
        heldItem.setVersionDetails(versionDetails);

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setHeldItems(Arrays.asList(heldItem));
        //pokemonResponse.setAbilities(Arrays.asList(abilityResponse));

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getHeldItemsRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getHeldItemsRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[{\"item\":{\"name\":\"Name\",\"url\":\"http\"},\"version_details\":[{\"rarity\":2}]}]</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }


    @Test
    void testIDSuccess() {
        // Mock the PokeApiClient response

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setId(100);
        //pokemonResponse.setAbilities(Arrays.asList(abilityResponse));

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getIdRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getIdRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>100</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testNameSuccess() {
        // Mock the PokeApiClient response

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setName("bulbasaur");

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getNameRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getNameRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>bulbasaur</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }


    @Test
    void testLocationAreaEncountersSuccess() {
        // Mock the PokeApiClient response

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setLocationAreaEncounters("Pallet Town");

        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(pokemonResponse, HttpStatus.OK));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getLocationAreaEncountersRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getLocationAreaEncountersRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>Pallet Town</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }



    @Test
    void testGetAbility_NotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getAbilityRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getAbilityRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testBaseExperienceNotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getBaseExperienceRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getBaseExperienceRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testHeldItemsRequestNotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getHeldItemsRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getHeldItemsRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testIDNotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getIdRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getIdRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testNameNotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getNameRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getNameRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }

    @Test
    void testLocationAreaEncountersNotFound() {
        // Mock the PokeApiClient response
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Create the request payload
        Source requestPayload = new StringSource(
                "<getLocationAreaEncountersRequest xmlns='http://pokesoap.com/soap/pokemon'><name>unknown</name></getLocationAreaEncountersRequest>");

        // Create the expected response payload
        Source expectedResponsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>404 NOT_FOUND</status><message>Not Found</message></GenericResponse>");

        // Perform the test
        mockClient.sendRequest(RequestCreators.withPayload(requestPayload))
                .andExpect(ResponseMatchers.payload(expectedResponsePayload));
    }



}
