package com.example.springbasicannotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewTemplateController {

    @RequestMapping("/response-view")
    public String responseView(Model model) {
        model.addAttribute("data", "sparta");

        return "thymeleaf-view";
    }

    // thymeleaf-view.html 과 Mapping된다.
    @RequestMapping("/thymeleaf-view")
    public void responseViewV2(Model model) {
        model.addAttribute("data", "sparta");
    }

}
