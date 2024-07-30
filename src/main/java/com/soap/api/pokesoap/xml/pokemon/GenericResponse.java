package com.soap.api.pokesoap.xml.pokemon;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"status", "message"}
)
@XmlRootElement(
        name = "GenericResponse"
)
public class GenericResponse {
    @XmlElement(
            required = true
    )
    protected String status;
    @XmlElement(
            required = true
    )
    protected String message;

    public GenericResponse() {
        // Do nothing because of X and Y.
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}
