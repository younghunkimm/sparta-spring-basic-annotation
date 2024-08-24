package com.example.springbasicannotation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class RequestParamControllerV2 {

    @ResponseBody
    @GetMapping("/v1/request-param")
    public String requestParamV1(
            @RequestParam("name") String userName,
            @RequestParam("age") int userAge
    ) {
        // logic
        log.info("name={}", userName);
        log.info("age={}", userAge);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v2/request-param")
    public String requestParamV2(
            @RequestParam String name,
            @RequestParam int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v3/request-param")
    public String requestParamV3(
            String name,
            int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v4/request-param")
    public String requestParamV4(
            @RequestParam(required = true) String name, // 필수
//            @RequestParam(required = false) int age	// 필수가 아님
            @RequestParam(required = false) Integer age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v5/request-param")
    public String requestParamV5(
            @RequestParam(required = true, defaultValue = "sparta") String name,
            @RequestParam(required = false, defaultValue = "1") int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v6/request-param")
    public String requestParamV6(
//            @RequestParam Map<String, String> map
            @RequestParam MultiValueMap<String, String> map
    ) {
        // logic

        log.info("name={}", map.get("name"));
        log.info("age={}", map.get("age"));

        return "success";
    }

}
