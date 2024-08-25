package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Tutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelAttributeController {

    @ResponseBody
    @PostMapping("/v1/tutor")
    public String requestParamV1(
            @RequestParam String name,
            @RequestParam int age
    ) {

        Tutor tutor = new Tutor();
        tutor.setName(name);
        tutor.setAge(age);

        return "tutor name = " + name + " age = " + age;
    }

    @ResponseBody
    @PostMapping("/v2/tutor")
    public String modelAttributeV2(
            @ModelAttribute Tutor tutor
    ) {

        String name = tutor.getName();
        int age = tutor.getAge();

        return "tutor name = " + name + " age = " + age;
    }

    @ResponseBody
    @PostMapping("/v3/tutor")
    public String modelAttributeV3(Tutor tutor) {

        String name = tutor.getName();
        int age = tutor.getAge();

        return "tutor name = " + name + " age = " + age;
    }

    @ResponseBody
    @PostMapping("/v4/tutor")
    public String requestParamV2(
            String name,
            int age
    ) {
        return "tutor name = " + name + " age = " + age;
    }

}
