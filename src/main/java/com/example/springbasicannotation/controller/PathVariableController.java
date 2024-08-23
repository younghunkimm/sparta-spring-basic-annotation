package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posts")
@RestController
public class PathVariableController {
    // postId로 된 post 단건 조회
    @GetMapping("/{postId}")
    public String pathVariableV1(@PathVariable("postId") Long data) {
        // logic
        String result = "PathvariableV1 결과입니다 : " + data;
        return result;
    }

}
