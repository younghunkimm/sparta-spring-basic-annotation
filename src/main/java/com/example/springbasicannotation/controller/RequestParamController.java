package com.example.springbasicannotation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @GetMapping("/request-params")
    public void params(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String key1Value = request.getParameter("key1");
        String key2Value = request.getParameter("key2");

        log.info("key1Value={}, key2Value={}", key1Value, key2Value);
        response.getWriter().write("success");
    }

    @PostMapping("/form-data")
    public void requestBody(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String key1Value = request.getParameter("key1");
        String key2Value = request.getParameter("key2");

        log.info("key1Value={}, key2Value={}", key1Value, key2Value);
        response.getWriter().write("success");
    }

}
