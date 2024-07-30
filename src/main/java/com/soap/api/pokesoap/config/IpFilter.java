package com.soap.api.pokesoap.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class IpFilter implements Filter {

    private static final ThreadLocal<String> clientIp = new ThreadLocal<>();

    public static String getClientIp() {
        return clientIp.get();
    }

    public static void clear() {
        clientIp.remove();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            String ipAddress = request.getRemoteAddr();
            clientIp.set(ipAddress);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            clear();
        }
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
}