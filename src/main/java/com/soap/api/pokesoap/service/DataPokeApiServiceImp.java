package com.soap.api.pokesoap.service;


import com.soap.api.pokesoap.entity.DataPokeApi;
import com.soap.api.pokesoap.repository.DataPokeApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataPokeApiServiceImp implements  DataPokeApiService{


    private final DataPokeApiRepository dataPokeApiRepository;

    public DataPokeApiServiceImp(DataPokeApiRepository dataPokeApiRepository) {
        this.dataPokeApiRepository = dataPokeApiRepository;
    }

    @Override
    public DataPokeApi placeData(DataPokeApi dataPokeApi) {

       return dataPokeApiRepository.save(dataPokeApi);

    }
}
