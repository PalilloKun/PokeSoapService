package com.soap.api.pokesoap.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class OpenApiConfig {

    private final ResourceLoader resourceLoader;

    public OpenApiConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        try {
            // Reading swagger.yml file from resources path
            Resource resource = resourceLoader.getResource("classpath:swagger.yml");
            InputStream inputStream = resource.getInputStream();

            // Load yaml file content
            String yamlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            SwaggerParseResult result = new OpenAPIV3Parser().readContents(yamlContent);
            OpenAPI openAPI = result.getOpenAPI();
            // Set additional info if necessary
            openAPI.setInfo(new Info()
                    .title("PokemonSoapService")
                    .version("1.0.0")
                    .description("""
                        This project is part of challange, it exposes SOAP service that receive a string (name of the Pokemon) and also expose 6 different methods:
                        **abilities**
                        **base_experience**
                        **held_items**
                        **id**
                        **name**
                        **location_area_encounters**"""));

            return openAPI;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load swagger.yml: " + e.getMessage(), e);
        }
    }

}