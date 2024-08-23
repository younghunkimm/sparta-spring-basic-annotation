package com.example.springbasicannotation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @GetMapping("/request/headers")
    public String headers(
            HttpServletRequest request, // Servlet에서 사용한것과 같음
            HttpServletResponse response, // Servlet에서 사용한것과 같음
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "cookie", required = false) String cookie,
            HttpMethod httpMethod,
            Locale locale
    ) {
        // Servlet
        log.info("request={}", request);
        log.info("response={}", response);

        // @RequestHeader
        log.info("headerMap={}", headerMap);
        log.info("host={}", host);

        // @CookieValue
        log.info("cookie={}", cookie);

        // HttpMethod
        log.info("httpMethod={}", httpMethod);

        // Locale
        log.info("Locale={}", locale);

        return "success";
    }

}
