package com.soap.api.pokesoap.repository;


import com.soap.api.pokesoap.entity.DataPokeApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPokeApiRepository extends CrudRepository<DataPokeApi, Long> {
}
