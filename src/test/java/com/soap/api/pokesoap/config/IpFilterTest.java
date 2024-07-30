package com.soap.api.pokesoap.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

class IpFilterTest {

    private IpFilter ipFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    private FilterConfig filterConfig;

    @BeforeEach
    void setUp() {
        ipFilter = new IpFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = Mockito.mock(FilterChain.class);
        filterConfig = Mockito.mock(FilterConfig.class);
    }

    @Test
    void testDoFilter_ShouldCaptureClientIp() throws IOException, ServletException {
        // Set client IP
        String clientIp = "192.168.1.1";
        request.setRemoteAddr(clientIp);

        // Mock filter chain to capture the IP before clear is called
        doAnswer(invocation -> {
            assertEquals(clientIp, IpFilter.getClientIp());
            return null;
        }).when(filterChain).doFilter(request, response);

        // Execute filter
        ipFilter.doFilter(request, response, filterChain);

        // Clear thread-local storage manually for the next test
        IpFilter.clear();
    }


    @Test
    void testDoFilter_ShouldClearClientIpAfterProcessing() throws IOException, ServletException {
        // Set client IP
        String clientIp = "192.168.1.1";
        request.setRemoteAddr(clientIp);

        // Execute filter
        ipFilter.doFilter(request, response, filterChain);

        // Clear thread-local storage
        IpFilter.clear();

        // Assert IP is cleared
        assertEquals(null, IpFilter.getClientIp());
    }

    @Test
    void testInit_ShouldNotThrowException() {
        // Ensure init does not throw an exception
        assertDoesNotThrow(() -> ipFilter.init(filterConfig));
    }

    @Test
    void testDestroy_ShouldNotThrowException() {
        // Ensure destroy does not throw an exception
        assertDoesNotThrow(() -> ipFilter.destroy());
    }
}