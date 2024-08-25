package com.example.springbasicannotation.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Controller
public class RequestBodyStringController {

    @PostMapping("/v1/request-body-text")
    public void requestBodyTextV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        response.getWriter().write("response = " + bodyText);

    }

    @PostMapping("/v2/request-body-text")
    public void requestBodyTextV2(
            InputStream inputStream,
            Writer responseWriter
    ) throws IOException {

        String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        responseWriter.write("response = " + bodyText);

    }

    @PostMapping("/v3/request-body-text")
    public HttpEntity<String> requestBodyTextV3(HttpEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();

        return new HttpEntity<>("response = " + body); // 매개변수 = Body Message
    }

    @PostMapping("/v4/request-body-text")
    public HttpEntity<String> requestBodyTextV4(RequestEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();
        // url, method 사용 가능

        return new ResponseEntity<>("response = " + body, HttpStatus.CREATED); // Body Data, 상태코드
    }

    @ResponseBody
    @PostMapping("/v5/request-body-text")
    public String requestBodyTextV5(
            @RequestBody String body,
            @RequestHeader HttpHeaders headers
    ) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String bodyMessage = body;

        return "request header = " + headers + " response body = " + bodyMessage;
    }

}
