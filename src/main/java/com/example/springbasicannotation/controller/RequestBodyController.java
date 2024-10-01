package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Board;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyController {

    // JSON을 객체로 변환해주는 Jackson 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body")
    public void requestBody(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        Board board = objectMapper.readValue(messageBody, Board.class);

        log.info("board.getTitle()={}, board.getContent()={}", board.getTitle(), board.getContent());

        response.getWriter().write("success");

    }

}
