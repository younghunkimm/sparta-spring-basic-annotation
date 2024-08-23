package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterController {

    // parms 속성값 추가
    @GetMapping(value = "/users", params = "gender=man")
    public String params() {
        // logic
        String result = "params API가 호출 되었습니다.";
        return result;
    }

}
