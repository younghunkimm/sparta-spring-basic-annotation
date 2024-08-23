package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterController {

    // parms 속성값 추가
//    @GetMapping(value = "/users", params = "gender=man")
//    public String params() {
//        // logic
//        String result = "params API가 호출 되었습니다.";
//        return result;
//    }

    // headers 속성값 추가
//    @PostMapping(value = "/users", headers = "Content-Type=application/json")
//    public String headers() {
//        // logic
//        String result = "headers API가 호출 되었습니다.";
//        return result;
//    }

    // consumes 속성값 추가
//    @PostMapping(value = "/users", consumes = "application/json") // MediaType.APPLICATION_JSON_VALUE
//    public String consumes() {
//        // logic
//        String result = "consumes API가 호출 되었습니다.";
//        return result;
//    }

    // produces 속성값 추가
    @GetMapping(value = "/users", produces = "text/plain")
    public String produces() {
        // logic
        String result = "text/plain 데이터 응답";
        return result;
    }

}
