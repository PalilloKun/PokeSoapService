package com.soap.api.pokesoap.endpoint;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soap.api.pokesoap.external.client.PokeApiClient;
import com.soap.api.pokesoap.external.response.PokemonResponse;
import com.soap.api.pokesoap.interceptor.SoapRequestInterceptor;
import com.soap.api.pokesoap.util.Constants;
import com.soap.api.pokesoap.xml.pokemon.GenericResponse;
import com.soap.api.pokesoap.xml.pokemon.GetAbilityRequest;
import com.soap.api.pokesoap.xml.pokemon.GetBaseExperienceRequest;
import com.soap.api.pokesoap.xml.pokemon.GetHeldItemsRequest;
import com.soap.api.pokesoap.xml.pokemon.GetIdRequest;
import com.soap.api.pokesoap.xml.pokemon.GetLocationAreaEncountersRequest;
import com.soap.api.pokesoap.xml.pokemon.GetNameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class PokemonEndPoint {

    private static final String NAMESPACE_URI = "http://pokesoap.com/soap/pokemon";

    private final PokeApiClient pokeApiClient;

    public PokemonEndPoint(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilityRequest")
    @ResponsePayload
    public GenericResponse getAbility(@RequestPayload GetAbilityRequest request) throws JsonProcessingException {

        SoapRequestInterceptor.setCustomSoapMethod("getAbilityRequest");

        log.info("Calling pokemon API 1");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                getJson(model.getBody().getAbilities()) :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBaseExperienceRequest")
    @ResponsePayload
    public GenericResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
        SoapRequestInterceptor.setCustomSoapMethod("getBaseExperienceRequest");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                String.valueOf(model.getBody().getBaseExperience()) :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeldItemsRequest")
    @ResponsePayload
    public GenericResponse getHeldItems(@RequestPayload GetHeldItemsRequest request) throws JsonProcessingException {
        SoapRequestInterceptor.setCustomSoapMethod("getHeldItemsRequest");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                getJson(model.getBody().getHeldItems()) :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
    @ResponsePayload
    public GenericResponse getId(@RequestPayload GetIdRequest request) {
        SoapRequestInterceptor.setCustomSoapMethod("getIdRequest");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                String.valueOf(model.getBody().getId()) :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
    @ResponsePayload
    public GenericResponse getName(@RequestPayload GetNameRequest request) {
        SoapRequestInterceptor.setCustomSoapMethod("getNameRequest");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                model.getBody().getName().toString() :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationAreaEncountersRequest")
    @ResponsePayload
    public GenericResponse getName(@RequestPayload GetLocationAreaEncountersRequest request) {
        SoapRequestInterceptor.setCustomSoapMethod("getLocationAreaEncountersRequest");

        ResponseEntity<PokemonResponse> model = pokeApiClient.callPokeApiModel(request.getName());

        GenericResponse response = new GenericResponse();
        response.setStatus(model.getStatusCode().toString());
        response.setMessage((model.getStatusCode().value() == 200) ?
                model.getBody().getLocationAreaEncounters().toString() :
                Constants.NOT_FOUND_RESPONSE);

        return response;
    }

    private String getJson(Object data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }


}

