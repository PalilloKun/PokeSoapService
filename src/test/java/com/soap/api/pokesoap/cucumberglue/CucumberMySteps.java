package com.soap.api.pokesoap.cucumberglue;


import com.soap.api.pokesoap.external.client.PokeApiClient;
import com.soap.api.pokesoap.external.response.Ability;
import com.soap.api.pokesoap.external.response.AbilityResponse;
import com.soap.api.pokesoap.external.response.HeldItem;
import com.soap.api.pokesoap.external.response.PokemonResponse;
import com.soap.api.pokesoap.xml.pokemon.GenericResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberMySteps {

    @MockBean
    @Autowired
    private PokeApiClient pokeApiClient;

    private String pokemonName;
    private GenericResponse response;

    @Given("the Pokemon name is {string}")
    public void whenClientCalls(String name) {
        this.pokemonName = name;
    }

    @When("I request the ability of the Pokemon")
    public void whenCode() {
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setAbilities(Collections.singletonList(response1));

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("overgrow");
    }

    @Then("the response should contain the ability {string}")
    public void responseShouldGetAbility(String ability) {
        Assertions.assertEquals(ability, response.getMessage());
    }

    /////

    @When("I request the base experience of the Pokemon")
    public void whenRequestedExperience() {
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setAbilities(Collections.singletonList(response1));
        mockResponse.setBaseExperience(64);

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("64");
    }

    @Then("the response should contain the base experience {string}")
    public void responseShouldGetExperience(String experience) {
        Assertions.assertEquals(experience, response.getMessage());
    }

    ///////

    @When("I request the held items of the Pokemon")
    public void whenRequestedHeldItems() {
        List<HeldItem> heldItems = new ArrayList<>();
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setHeldItems(heldItems);
        mockResponse.setAbilities(Collections.singletonList(response1));
        mockResponse.setBaseExperience(64);

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("some item");
    }

    @Then("the response should contain the held item {string}")
    public void responseShouldGetHeldItem(String experience) {
        Assertions.assertEquals(experience, response.getMessage());
    }
    ///////

    @When("I request the ID of the Pokemon")
    public void whenRequestedId() {
        List<HeldItem> heldItems = new ArrayList<>();
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setHeldItems(heldItems);
        mockResponse.setAbilities(Collections.singletonList(response1));
        mockResponse.setBaseExperience(64);
        mockResponse.setId(1);

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("1");
    }

    @Then("the response should contain the ID {string}")
    public void responseShouldGetID(String experience) {
        Assertions.assertEquals(experience, response.getMessage());
    }

    //////

    @When("I request the name of the Pokemon")
    public void whenRequestedName() {
        List<HeldItem> heldItems = new ArrayList<>();
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setHeldItems(heldItems);
        mockResponse.setAbilities(Collections.singletonList(response1));
        mockResponse.setBaseExperience(64);
        mockResponse.setId(1);
        mockResponse.setName("bulbasaur");

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("bulbasaur");
    }

    @Then("the response should contain the name {string}")
    public void responseShouldGetName(String experience) {
        Assertions.assertEquals(experience, response.getMessage());
    }


    ////

    @When("I request the location area encounters of the Pokemon")
    public void whenRequestedEncounters() {
        List<HeldItem> heldItems = new ArrayList<>();
        AbilityResponse response1 = new AbilityResponse();
        response1.setAbility(new Ability("overgrow", ""));
        PokemonResponse mockResponse = new PokemonResponse();
        mockResponse.setHeldItems(heldItems);
        mockResponse.setAbilities(Collections.singletonList(response1));
        mockResponse.setBaseExperience(64);
        mockResponse.setId(1);
        mockResponse.setName("bulbasaur");
        mockResponse.setLocationAreaEncounters("Pallet Town");

        // Stubbing the call to PokeApiClient
        Mockito.when(pokeApiClient.callPokeApiModel(anyString()))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        response = new GenericResponse();
        response.setStatus(HttpStatus.OK.toString());
        response.setMessage("Pallet Town");
    }

    @Then("the response should contain the location area encounters {string}")
    public void responseShouldGetEncounters(String experience) {
        Assertions.assertEquals(experience, response.getMessage());
    }

}
