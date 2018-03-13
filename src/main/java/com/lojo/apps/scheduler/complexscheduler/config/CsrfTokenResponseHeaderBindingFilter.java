package com.lojo.apps.scheduler.complexscheduler.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class CsrfTokenResponseHeaderBindingFilter extends OncePerRequestFilter {
    protected static final String REQUEST_ATTRIBUTE_NAME = "_csrf";
    protected static final String RESPONSE_HEADER_NAME = "X-CSRF-HEADER";
    protected static final String RESPONSE_PARAM_NAME = "X-CSRF-PARAM";
    protected static final String RESPONSE_TOKEN_NAME = "X-CSRF-TOKEN";

    private String attributes(HttpServletRequest request){
        String out="\n";

        List<String> attribs =Collections.list(request.getAttributeNames());

        for (String key: attribs) {
            Object value =request.getAttribute(key);
            out+=key+"->"+value.toString()+"\n";
        }

        return out;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, javax.servlet.FilterChain filterChain) throws ServletException, IOException {
        CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
        log.info("CsrfTokenResponseHeaderBindingFilter.doFilterInternal - {} ", request.getRequestURI());
        log.debug(attributes(request));

        if (token != null) {
            log.info("Setting csrf headers!");
            response.setHeader(RESPONSE_HEADER_NAME, token.getHeaderName());
            response.setHeader(RESPONSE_PARAM_NAME, token.getParameterName());
            response.setHeader(RESPONSE_TOKEN_NAME , token.getToken());
        }
        else {
            log.warn("Could not find csrf token for this request");
        }

        filterChain.doFilter(request, response);
    }
}
