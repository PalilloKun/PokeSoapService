package com.soap.api.pokesoap.external.client;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import com.soap.api.pokesoap.external.response.PokemonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@WireMockTest(httpPort = 8080)
class PokeApiClientTest {

    @Autowired
    private PokeApiClient pokeApiClient;


    @BeforeEach
    void setUp() {

        WebClient.Builder builder = WebClient.builder();
        pokeApiClient = new PokeApiClient(builder.baseUrl("http://localhost:8080/"));
    }

    @Test
    void testCallPokeApiModelSuccess() {
        String pokemonName = "pikachu";

        stubFor(get(urlEqualTo("/pokemon/" + pokemonName))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"name\": \"pikachu\"}")));

        ResponseEntity<PokemonResponse> response = pokeApiClient.callPokeApiModel(pokemonName);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("pikachu", response.getBody().getName());
    }

    @Test
    void testCallPokeApiModelNotFound() {
        String pokemonName = "unknown";

        stubFor(get(urlEqualTo("/pokemon/" + pokemonName))
                .willReturn(aResponse()
                        .withStatus(404)));

        ResponseEntity<PokemonResponse> response = pokeApiClient.callPokeApiModel(pokemonName);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}