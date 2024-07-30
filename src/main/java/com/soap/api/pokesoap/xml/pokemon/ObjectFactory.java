package com.soap.api.pokesoap.xml.pokemon;


import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
        // Do nothing because of X and Y.
    }

    public GetAbilityRequest createGetAbilityRequest() {
        return new GetAbilityRequest();
    }

    public GetBaseExperienceRequest createGetBaseExperienceRequest() {
        return new GetBaseExperienceRequest();
    }

    public GetHeldItemsRequest createGetHeldItemsRequest() {
        return new GetHeldItemsRequest();
    }

    public GetIdRequest createGetIdRequest() {
        return new GetIdRequest();
    }

    public GetNameRequest createGetNameRequest() {
        return new GetNameRequest();
    }

    public GetLocationAreaEncountersRequest createGetLocationAreaEncountersRequest() {
        return new GetLocationAreaEncountersRequest();
    }

    public GenericResponse createGenericResponse() {
        return new GenericResponse();
    }
}
