package com.soap.api.pokesoap.xml.pokemon;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"name"}
)
@XmlRootElement(
        name = "getLocationAreaEncountersRequest"
)
public class GetLocationAreaEncountersRequest {
    @XmlElement(
            required = true
    )
    protected String name;

    public GetLocationAreaEncountersRequest() {
        // Do nothing because of X and Y.
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
