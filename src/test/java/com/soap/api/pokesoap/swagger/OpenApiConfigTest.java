package com.soap.api.pokesoap.swagger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class OpenApiConfigTest {

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private OpenAPIV3Parser openAPIV3Parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void customOpenAPISuccess() throws Exception {
        // Prepare mock Resource and InputStream
        Resource resource = mock(Resource.class);
        InputStream inputStream = new ByteArrayInputStream("openapi: 3.0.0".getBytes(StandardCharsets.UTF_8));
        when(resource.getInputStream()).thenReturn(inputStream);
        when(resourceLoader.getResource("classpath:swagger.yml")).thenReturn(resource);
        OpenAPI mockOpenAPI = new OpenAPI();
        SwaggerParseResult mockParseResult = new SwaggerParseResult();
        mockParseResult.setOpenAPI(mockOpenAPI);

        when(openAPIV3Parser.readContents(anyString())).thenReturn(mockParseResult);

        OpenAPI openAPI = openApiConfig.customOpenAPI();
        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        assertTrue(openAPI.getInfo().getTitle().equals("PokemonSoapService"));
        assertTrue(openAPI.getInfo().getVersion().equals("1.0.0"));
        assertTrue(openAPI.getInfo().getDescription().contains("abilities"));
        verify(resourceLoader).getResource("classpath:swagger.yml");
        verify(resource).getInputStream();

    }

    @Test
    void customOpenAPIException() throws Exception {
        Resource resource = mock(Resource.class);
        InputStream inputStream = new ByteArrayInputStream("openapi: 3.0.0".getBytes(StandardCharsets.UTF_8));
        when(resource.getInputStream()).thenReturn(null);
        when(resourceLoader.getResource("classpath:swagger.yml")).thenReturn(resource);

        SwaggerParseResult mockParseResult = new SwaggerParseResult();
        mockParseResult.setOpenAPI(null);
        mockParseResult.setMessages(java.util.Collections.singletonList("Invalid YAML"));

        when(openAPIV3Parser.readContents(anyString())).thenReturn(mockParseResult);
        Exception exception = assertThrows(RuntimeException.class, () -> openApiConfig.customOpenAPI());
        assertFalse(exception.getMessage().contains("Failed to parse swagger.yml: [Invalid YAML]"));
    }
}