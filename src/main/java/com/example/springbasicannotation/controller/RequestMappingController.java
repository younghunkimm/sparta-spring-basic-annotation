package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 응답 데이터를 반환한다.
@RestController
public class RequestMappingController {

    // HTTP Method 는 GET만 허용한다.
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public String exampleV1() {
        // logic
        return "this is sparta!";
    }

}
