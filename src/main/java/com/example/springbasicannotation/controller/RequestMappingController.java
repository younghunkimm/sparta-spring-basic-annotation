package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.*;

// 응답 데이터를 반환한다.
//@RequestMapping("/prefix")
@RestController
public class RequestMappingController {

    // HTTP Method 는 GET만 허용한다.
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public String exampleV1() {
        // logic
        return "this is sparta!";
    }

    // Post, GET, Put, Patch, Delete 모두 가능
    @GetMapping(value = "/v2")
    public String exampleV2() {
        // logic
        return "this is sparta!";
    }

//    @PostMapping(value = "/v2")
//    public String exampleV2() {
//        // logic
//        return "this is sparta!";
//    }
//
//    @PutMapping(value = "/v2")
//    public String exampleV2() {
//        // logic
//        return "this is sparta!";
//    }
//
//    @PatchMapping(value = "/v2")
//    public String exampleV2() {
//        // logic
//        return "this is sparta!";
//    }
//
//    @DeleteMapping(value = "/v2")
//    public String exampleV2() {
//        // logic
//        return "this is sparta!";
//    }

//    // Post, GET, Put, Patch, Delete 모두 가능
//    @GetMapping(value = "/v3")
//    public String exampleV3() {
//        // logic
//        return "this is sparta!";
//    }

}
