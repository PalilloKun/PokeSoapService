package com.soap.api.pokesoap.config;

import com.soap.api.pokesoap.interceptor.SoapRequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    private final SoapRequestInterceptor soapRequestInterceptor;

    public WebServiceConfig(SoapRequestInterceptor soapRequestInterceptor) {
        this.soapRequestInterceptor = soapRequestInterceptor;
    }

    /**
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     *
     * @param productsSchema
     * @return
     */
    @Bean(name = "pokemons")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema productsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PokemonPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://pokesoap.com/soap/pokemon");
        wsdl11Definition.setSchema(productsSchema);
        return wsdl11Definition;
    }

    /**
     *
     * @return
     */
    @Bean
    public XsdSchema productsSchema() {

        return new SimpleXsdSchema(new ClassPathResource("pokesoapmethods.xsd"));
    }

    /**
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<IpFilter> ipFilter() {
        FilterRegistrationBean<IpFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IpFilter());
        registrationBean.addUrlPatterns("/ws/*");
        return registrationBean;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(soapRequestInterceptor);
    }


}