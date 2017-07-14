package com.zuora.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Kui on 2017/7/14.
 */

@Slf4j
@RestController
public class TestResource {

    @Autowired
    private RestTemplate restTemplate;

    private String server = "app-0.stg.eu.zuora.com";
    private int port = 443;

    @RequestMapping("/**")
    @ResponseBody
    public String mirrorRest(@RequestBody String body, HttpMethod method, HttpServletRequest request,
                             HttpServletResponse response) throws URISyntaxException {

        URI uri = new URI("https", null, server, port, request.getRequestURI(), request.getQueryString(), null);

        HttpHeaders headers = new HttpHeaders();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.add(key, value);
        }

        ResponseEntity<String> responseEntity =
            restTemplate.exchange(uri, method, new HttpEntity<>(body, headers), String.class);

        return responseEntity.getBody();
    }
}
