package com.example.springbasicannotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/view")
    public String example() {
        // logic
        return "sparta"; // ViewName이 return
    }

}
