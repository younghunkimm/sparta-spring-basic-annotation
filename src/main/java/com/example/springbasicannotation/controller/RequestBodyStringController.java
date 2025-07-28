package com.example.springbasicannotation.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * <h2>HTTP Message Body(요청)</h2>
 * <ul>
 *     <li>
 *         이제부터 배울 내용은 HTTP Message Body에 직접적으로 Data가 전달되는 경우이다.
 *         <ul>
 *             <li>Request Body의 Data를 바인딩하는 방법이다.</li>
 *         </ul>
 *     </li>
 *     <li>REST API에서 주로 사용하는 방식이다.</li>
 *     <li>
 *         HTTP Method POST, PUT, PATCH 에서 주로 사용한다.
 *         <ul>
 *             <li>GET은 Request Body가 존재할 수는 있지만 권장하지 않는다.</li>
 *         </ul>
 *     </li>
 *     <li><b>JSON</b>, XML, TEXT 등을 데이터 형식으로 사용한다.</li>
 *     <li>
 *         HTTP Request, Response
 *         <ul>
 *             <li>
 *                 <p>Server에서 Request로 전달받은 Data를 처리하기 위해서 바인딩 해야 한다.</p>
 *                 <p>Ex) JSON -> Object</p>
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 */
@Controller
public class RequestBodyStringController {

    /**
     * <ul>
     *     <li>HTTP Request Body에 Data가 전송되는 경우 {@link HttpMessageConverter}를 통해 바인딩된다.</li>
     *     <li>현대에서는 Restful API를 주로 사용하고 있어서 대부분의 경우 <b>JSON</b> 형식으로 통신한다.</li>
     * </ul>
     * <br>
     * <h2>HttpServletRequest 예시</h2>
     * <ul>
     *     <li>{@code request.getInputStream();}</li>
     *     <li>
     *         Postman
     *         <ul>
     *             <li>Request -> Body -> raw -> Text</li>
     *         </ul>
     *     </li>
     *     <li>{@code Request Header Content-Type: text/plain}</li>
     * </ul>
     */
    @PostMapping("/v1/request-body-text")
    public void requestBodyTextV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        response.getWriter().write("response = " + bodyText);

    }

    @PostMapping("/v2/request-body-text")
    public void requestBodyTextV2(
            InputStream inputStream,
            Writer responseWriter
    ) throws IOException {

        String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        responseWriter.write("response = " + bodyText);

    }

    @PostMapping("/v3/request-body-text")
    public HttpEntity<String> requestBodyTextV3(HttpEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();

        return new HttpEntity<>("response = " + body); // 매개변수 = Body Message
    }

    @PostMapping("/v4/request-body-text")
    public HttpEntity<String> requestBodyTextV4(RequestEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();
        // url, method 사용 가능

        return new ResponseEntity<>("response = " + body, HttpStatus.CREATED); // Body Data, 상태코드
    }

    @ResponseBody
    @PostMapping("/v5/request-body-text")
    public String requestBodyTextV5(
            @RequestBody String body,
            @RequestHeader HttpHeaders headers
    ) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String bodyMessage = body;

        return "request header = " + headers + " response body = " + bodyMessage;
    }

}
