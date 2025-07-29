package com.example.springbasicannotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @ResponseBody // @RestController = @Controller + @ResponseBody
    @RequestMapping("/response-body")
    public String responseBody() {
        return "thymeleaf-view";
    }

    @RequestMapping("/view")
    public String example() {
        // logic
        return "sparta"; // ViewName이 return
    }

}
