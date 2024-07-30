package com.soap.api.pokesoap.config;


import com.soap.api.pokesoap.interceptor.SoapRequestInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
class WebServiceConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebServiceConfig webServiceConfig;

    @Autowired
    private SoapRequestInterceptor soapRequestInterceptor;

    @Test
    void testMessageDispatcherServletBean() {
        ServletRegistrationBean<MessageDispatcherServlet> bean = webServiceConfig.messageDispatcherServlet(applicationContext);
        assertNotNull(bean);
        assertNotNull(bean.getServlet());
        assertNotNull(bean.getUrlMappings());
    }

    @Test
    void testDefaultWsdl11DefinitionBean() {
        XsdSchema schema = mock(XsdSchema.class);
        DefaultWsdl11Definition definition = webServiceConfig.defaultWsdl11Definition(schema);
        assertNotNull(definition);

    }

    @Test
    void testProductsSchemaBean() {
        XsdSchema schema = webServiceConfig.productsSchema();
        assertNotNull(schema);
    }

    @Test
    void testIpFilterBean() {
        FilterRegistrationBean<IpFilter> bean = webServiceConfig.ipFilter();
        assertNotNull(bean);
        assertNotNull(bean.getFilter());
        assertNotNull(bean.getUrlPatterns());
    }

    @Test
    void testAddInterceptors() {
        List<EndpointInterceptor> interceptors = mock(List.class);
        webServiceConfig.addInterceptors(interceptors);
        assertNotNull(interceptors);
    }
}