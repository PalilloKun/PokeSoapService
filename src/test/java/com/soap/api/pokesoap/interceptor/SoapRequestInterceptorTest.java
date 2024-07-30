package com.soap.api.pokesoap.interceptor;


import com.soap.api.pokesoap.entity.DataPokeApi;
import com.soap.api.pokesoap.service.DataPokeApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@Slf4j
class SoapRequestInterceptorTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private DataPokeApiService dataPokeApiService;

    private MockWebServiceClient mockClient;

    private SoapRequestInterceptor interceptor;

    @BeforeEach
    void setup() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
        interceptor = new SoapRequestInterceptor(dataPokeApiService);
    }

    @Test
    void testHandleRequest() throws Exception {
        // Mock MessageContext and SaajSoapMessage
        MessageContext messageContext = Mockito.mock(MessageContext.class);
        SaajSoapMessage saajSoapMessage = Mockito.mock(SaajSoapMessage.class);

        // Create a sample SOAP request payload
        Source requestPayload = new StringSource("<getAbilityRequest xmlns='http://pokesoap.com/soap/pokemon'><name>bulbasaur</name></getAbilityRequest>");

        // Mock the behavior of messageContext and saajSoapMessage
        when(messageContext.getRequest()).thenReturn(saajSoapMessage);
        when(saajSoapMessage.getPayloadSource()).thenReturn(requestPayload);

        // Execute handleRequest
        assertDoesNotThrow(() -> {
            boolean result = interceptor.handleRequest(messageContext, new Object());
            assertTrue(result);
        });

        // Verify that the request payload was captured
        String capturedPayload = SoapRequestInterceptor.getRequestPayload();
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(requestPayload, new StreamResult(writer));
        String expectedPayload = writer.toString();

        assertTrue(capturedPayload.contains(expectedPayload));


        // Clear thread-local storage for the next test
        SoapRequestInterceptor.clear();
    }

    @Test
    void testHandleResponse() throws Exception {
        MessageContext messageContext = Mockito.mock(MessageContext.class);
        SaajSoapMessage saajSoapMessage = Mockito.mock(SaajSoapMessage.class);

        // Create a sample SOAP response payload
        Source responsePayload = new StringSource(
                "<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[AbilityResponse(ability=Ability(name=hidden, url=http), is_hidden=false, slot=0)]</message></GenericResponse>");

        // Mock the behavior of messageContext and saajSoapMessage
        when(messageContext.getResponse()).thenReturn(saajSoapMessage);
        when(saajSoapMessage.getPayloadSource()).thenReturn(responsePayload);

        // Set custom SOAP method and request payload for testing
        SoapRequestInterceptor.setCustomSoapMethod("getAbilityRequest");
        SoapRequestInterceptor.setResponsePayload("<GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[AbilityResponse(ability=Ability(name=hidden, url=http), is_hidden=false, slot=0)]</message></GenericResponse>");
        SoapRequestInterceptor.setStartTime(System.currentTimeMillis() - 1000); // Mock start time

        // Execute handleResponse
        assertDoesNotThrow(() -> {
            boolean result = interceptor.handleResponse(messageContext, new Object());
            assertEquals(true, result);
        });

        // Capture the DataPokeApi object saved by the service
        ArgumentCaptor<DataPokeApi> captor = ArgumentCaptor.forClass(DataPokeApi.class);
        verify(dataPokeApiService).placeData(captor.capture());
        DataPokeApi savedData = captor.getValue();
        assertEquals("getAbilityRequest", savedData.getMethodExecution());

        // assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><GenericResponse xmlns='http://pokesoap.com/soap/pokemon'><status>200 OK</status><message>[AbilityResponse(ability=Ability(name=hidden, url=http), is_hidden=false, slot=0)]</message></GenericResponse>", savedData.getResponse());
        assertEquals(true, savedData.getTimeExecution().endsWith(" ms"));


        SoapRequestInterceptor.clear();
    }


    @Test
    void testHandleFault_ShouldNotThrowException() {
        SoapRequestInterceptor interceptor = new SoapRequestInterceptor(dataPokeApiService);
        MessageContext messageContext = Mockito.mock(MessageContext.class);
        Object endpoint = new Object();

        assertDoesNotThrow(() -> interceptor.handleFault(messageContext, endpoint));
    }

    @Test
    void testAfterCompletion_ShouldNotThrowException() {
        SoapRequestInterceptor interceptor = new SoapRequestInterceptor(dataPokeApiService);
        MessageContext messageContext = Mockito.mock(MessageContext.class);
        Object endpoint = new Object();

        assertDoesNotThrow(() -> interceptor.afterCompletion(messageContext, endpoint, null));
    }
}