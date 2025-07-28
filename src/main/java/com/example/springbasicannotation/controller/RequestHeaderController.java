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

            // MultiValueMap 형태로 header의 정보들을 확인할 수 있다.
            // MultiValueMap: Key가 여러 Value를 가질 수 있다.
            @RequestHeader MultiValueMap<String, String> headerMap,

            // header의 정보 중 host의 정보만 가져온다.
            @RequestHeader("host") String host,

            // header의 쿠키의 값을 가져온다.
            // required가 true라면 항상 쿠키가 있어야 메서드가 실행된다.
            @CookieValue(value = "cookie", required = false) String cookie,

            // 호출에 사용한 HttpMethod를 확인할 수 있다.
            HttpMethod httpMethod,

            // 위치 정보를 나타내는 헤더이다.
            // 우선순위가 존재한다.
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
