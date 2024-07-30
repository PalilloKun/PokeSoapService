package com.soap.api.pokesoap.repository;


import com.soap.api.pokesoap.entity.DataPokeApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class DataPokeApiRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DataPokeApiRepository dataPokeApiRepository;

    private DataPokeApi testDataPokeApi;

    @BeforeEach
    void setUp() {
        testDataPokeApi = DataPokeApi.builder()
                .originIp("127.0.0.1")
                .dateRequest(Instant.now())
                .methodExecution("getAbilityRequest")
                .timeExecution("123 ms")
                .request("<getAbilityRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getAbilityRequest>")
                .response("<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[AbilityResponse(ability=Ability(name=hidden, url=http), is_hidden=false, slot=0)]</message></GenericResponse>")
                .build();
    }

    @Test
    void testSaveDataPokeApi() {
        DataPokeApi savedDataPokeApi = dataPokeApiRepository.save(testDataPokeApi);
        assertEquals(testDataPokeApi.getOriginIp(), savedDataPokeApi.getOriginIp());
    }

    @Test
    void testFindById() {
        DataPokeApi savedDataPokeApi = dataPokeApiRepository.save(testDataPokeApi);
        Optional<DataPokeApi> retrievedDataPokeApi = dataPokeApiRepository.findById(savedDataPokeApi.getId());
        assertTrue(retrievedDataPokeApi.isPresent());
        assertEquals(savedDataPokeApi.getOriginIp(), retrievedDataPokeApi.get().getOriginIp());
    }

    @Test
    void testUpdateDataPokeApi() {
        DataPokeApi savedDataPokeApi = dataPokeApiRepository.save(testDataPokeApi);
        savedDataPokeApi.setMethodExecution("updatedMethod");
        DataPokeApi updatedDataPokeApi = dataPokeApiRepository.save(savedDataPokeApi);
        assertEquals("updatedMethod", updatedDataPokeApi.getMethodExecution());
    }

    @Test
    void testDeleteDataPokeApi() {
        DataPokeApi savedDataPokeApi = dataPokeApiRepository.save(testDataPokeApi);
        dataPokeApiRepository.delete(savedDataPokeApi);
        Optional<DataPokeApi> retrievedDataPokeApi = dataPokeApiRepository.findById(savedDataPokeApi.getId());
        assertTrue(retrievedDataPokeApi.isEmpty());
    }

    @Test
    void testSaveAndFindDataPokeApi() {
        // Save the entity
        DataPokeApi savedDataPokeApi = entityManager.persistFlushFind(testDataPokeApi);
        assertNotNull(savedDataPokeApi.getId());

        // Find the entity
        DataPokeApi foundDataPokeApi = dataPokeApiRepository.findById(savedDataPokeApi.getId()).orElse(null);
        assertNotNull(foundDataPokeApi);

        // Verify the entity fields
        assertEquals(savedDataPokeApi.getId(), foundDataPokeApi.getId());
        assertEquals(savedDataPokeApi.getOriginIp(), foundDataPokeApi.getOriginIp());
        assertEquals(savedDataPokeApi.getDateRequest(), foundDataPokeApi.getDateRequest());
        assertEquals(savedDataPokeApi.getMethodExecution(), foundDataPokeApi.getMethodExecution());
        assertEquals(savedDataPokeApi.getTimeExecution(), foundDataPokeApi.getTimeExecution());
        assertEquals(savedDataPokeApi.getRequest(), foundDataPokeApi.getRequest());
        assertEquals(savedDataPokeApi.getResponse(), foundDataPokeApi.getResponse());
    }
}