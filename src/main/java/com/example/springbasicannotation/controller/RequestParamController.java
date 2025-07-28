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

    /**
     * <h2><b>{@code HttpServletRequest} 사용</b></h2>
     *
     * <ul>
     *     <li>
     *         {@code response.getWriter().write()}
     *
     *         <ul>
     *             <li>HttpServletResponse를 사용해서 응답값을 직접 다룰 수 있다.</li>
     *             <li>{@code @Controller} 지만 {@code @ResponseBody} 를 함께 사용한 것과 같다.</li>
     *         </ul>
     *     </li>
     * </ul>
     */
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
