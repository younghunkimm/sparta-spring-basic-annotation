package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Tutor;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Controller
public class ResponseBodyController {

    @GetMapping("/v1/response-body")
    public void responseBodyV1(
            HttpServletResponse response
    ) throws IOException {
        // HttpServletResponse 객체를 사용한다.
        response.getWriter().write("data");
    }

    @GetMapping("/v2/response-body")
    public ResponseEntity<String> responseBodyV2() {

        return new ResponseEntity<>("data", HttpStatus.OK);
    }

    // TEXT 데이터 통신
    @ResponseBody
    @GetMapping("/v3/response-body-text")
    public String responseBodyText() {

        return "data"; // HTTP Message Body에 "data"
    }

    // JSON 데이터 통신
    @ResponseBody
    @GetMapping("/v3/response-body-json")
    public Tutor responseBodyJson() {

        Tutor tutor = new Tutor("wonuk", 100);

        return tutor; // HTTP Message Body에 Member Object -> JSON
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/v4/response-body")
    public Tutor responseBodyV4() {

        Tutor tutor = new Tutor("wonuk", 100);

        return tutor;
    }

    @ResponseBody
    @GetMapping("/v5/response-body")
    public ResponseEntity<Tutor> responseBody() {

        Tutor tutor = new Tutor("wonuk", 100);

        return new ResponseEntity<>(tutor, HttpStatus.OK);
    }

}
