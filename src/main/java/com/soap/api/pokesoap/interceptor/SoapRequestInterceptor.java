package com.soap.api.pokesoap.interceptor;

import com.soap.api.pokesoap.config.IpFilter;
import com.soap.api.pokesoap.entity.DataPokeApi;
import com.soap.api.pokesoap.service.DataPokeApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.time.Instant;

@Component
@Slf4j
public class SoapRequestInterceptor implements EndpointInterceptor {

    private final DataPokeApiService pokeApiService;

    @Autowired
    public SoapRequestInterceptor(DataPokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    private static final ThreadLocal<String> requestPayload = new ThreadLocal<>();
    private static final ThreadLocal<String> responsePayload = new ThreadLocal<>();
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final ThreadLocal<Long> endTime = new ThreadLocal<>();

    private static final ThreadLocal<String> customSoapMethod = new ThreadLocal<>();

    public static String getRequestPayload() {
        return requestPayload.get();
    }

    public static String getResponsePayload() {
        return responsePayload.get();
    }

    public static String getCustomSoapMethod() {
        return customSoapMethod.get();
    }

    public static void setCustomSoapMethod(String value) {
        customSoapMethod.set(value);
    }

    public static void setResponsePayload(String value) {
        responsePayload.set(value);
    }

    public static void setStartTime(Long tm){
        startTime.set(tm);
    }


    public static void clear() {
        requestPayload.remove();
        responsePayload.remove();
        startTime.remove();
        endTime.remove();
        customSoapMethod.remove();
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(soapMessage.getPayloadSource(), new StreamResult(writer));
        requestPayload.set(writer.toString());
        startTime.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        endTime.set(System.currentTimeMillis());

        SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getResponse();
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(soapMessage.getPayloadSource(), new StreamResult(writer));
        String responsePayloadString = writer.toString();
        responsePayload.set(responsePayloadString);

        // Create and save DataPokeApi object
        DataPokeApi pokeApiDB = DataPokeApi.builder()
                .dateRequest(Instant.now())
                .methodExecution(getCustomSoapMethod())
                .originIp(IpFilter.getClientIp())
                .request(getRequestPayload())
                .response(responsePayloadString)
                .timeExecution((endTime.get() - startTime.get()) + " ms")
                .build();

        pokeApiService.placeData(pokeApiDB);
        log.info("saved");

        clear(); // Ensure the payload is cleared after logging
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        clear();
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        clear();
    }

}
