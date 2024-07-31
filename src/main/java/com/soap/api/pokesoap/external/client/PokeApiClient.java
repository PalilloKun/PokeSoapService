package com.soap.api.pokesoap.external.client;

import com.soap.api.pokesoap.external.response.PokemonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PokeApiClient {

    private static final String URL_POKEMON = "pokemon/";
    private final WebClient pokeApiClient;

    public PokeApiClient(WebClient.Builder builder) {
        this.pokeApiClient = builder .baseUrl("https://pokeapi.co/api/v2/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, "*/*")
                .build();
    }

    /**
     *
     * @param pokemonName
     * @return
     */
    public ResponseEntity<PokemonResponse> callPokeApiModel(String pokemonName){
        return pokeApiClient.get()
                .uri(URL_POKEMON + pokemonName)
                .retrieve()
                .toEntity(PokemonResponse.class)
                .onErrorReturn(new ResponseEntity<>(new PokemonResponse(), HttpStatus.NOT_FOUND))
                .block();
    }
}
