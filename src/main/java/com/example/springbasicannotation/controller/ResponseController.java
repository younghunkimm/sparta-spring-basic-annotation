package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/string")
    public String example() {
        // logic
        return "sparta"; // ViewName이 return 되는게 아니라, String Data가 반환된다.
    }

}
