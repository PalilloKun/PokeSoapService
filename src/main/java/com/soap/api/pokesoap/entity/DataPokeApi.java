package com.soap.api.pokesoap.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataPokeApi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "origin_ip")
    private String originIp;

    @Column(name = "date_request")
    private Instant dateRequest;

    @Column(name = "method_execution")
    private String methodExecution;

    @Column(name = "time_execution")
    private String timeExecution;

    @Column(name = "request")
    private String request;

    @Column(name = "response", columnDefinition = "TEXT")
    private String response;

}
