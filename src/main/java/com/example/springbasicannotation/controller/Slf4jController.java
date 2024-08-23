package com.example.springbasicannotation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Slf4jController {

    @RequestMapping("/logging")
    public String logging() {

        String sparta = "Sparta";
        // TRACE -> DEBUG -> INFO -> WARN -> ERROR
        log.trace("문자 trace={}", sparta);
        log.debug("문자 debug={}", sparta);

        // default
        log.info("문자 info={}", sparta);// 문자 연산을 진행하지 않는다.
        log.warn("문자 warn={}", sparta);
        log.error("문자 error={}", sparta);

        log.info("문자 info " + sparta); // 문자 연산을 먼저 해버린다.
        return "success";
    }

}
