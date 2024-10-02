package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Tutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class JsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/v1/request-body-json")
    public void requestBodyJsonV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        // request body message를 Read
        ServletInputStream inputStream = request.getInputStream();
        // UTF-8 형식의 String으로 변환한다.
        String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // String requestBody를 ObjectMapper를 사용하여 변환 "{\"name\":\"wonuk\", \"age\":10}"
        Tutor tutor = objectMapper.readValue(requestBody, Tutor.class);

        response.getWriter().write("tutor" + tutor);
    }

    @PostMapping("/v2/request-body-json")
    public String requesBodyJsonV2(@RequestBody String requestBody) throws IOException {

        Tutor tutor = objectMapper.readValue(requestBody, Tutor.class);

        return "tutor.getName() = " + tutor.getName() + " tutor.getAge() = " + tutor.getAge();
    }

    @PostMapping("/v3/request-body-json")
    public String requestBodyJsonV3(@RequestBody Tutor tutor) {

        Tutor requestBodyTutor = tutor;

        return "tutor = " + requestBodyTutor;
    }

    @PostMapping("/v4/request-body-json")
    public String requestBodyJsonV4(Tutor tutor) { // @RequestBody 생략시 @ModelAttribute가 된다.

        Tutor requestBodyTutor = tutor;

        return "tutor.getName() = " + requestBodyTutor.getName() + " tutor.getAge() = " + requestBodyTutor.getAge();
    }

    @PostMapping("/v5/request-body-json")
    public String requestBodyJsonV5(
            HttpEntity<Tutor> httpEntity
    ) {
        // 값을 꺼내서 사용해야한다!
        Tutor tutor = httpEntity.getBody();

        return "tutor.getName() = " + tutor.getName() + " tutor.getAge() = " + tutor.getAge();
    }

    // @RestController = @Controller + @ResponseBody
    @PostMapping("/v6/request-body-json")
    public Tutor requestJson(@RequestBody Tutor tutor) {
        return tutor;
    }

}
