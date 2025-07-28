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
@Controller // @RestControll = @Controller + @ResponseBody
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

    /**
     * <h2>I/O 예시</h2>
     * <br>
     * <ul>
     *     <li>
     *         {@code InputStream(읽기)} 파라미터 지원
     *         <ul>
     *             <li>HTTP Request Body Data 직접 조회</li>
     *         </ul>
     *     </li>
     *     <li>
     *         {@code OutputStream(쓰기)} 파라미터 지원
     *         <ul>
     *             <li>HTTP Response Body 직접 결과 출력</li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @PostMapping("/v2/request-body-text")
    public void requestBodyTextV2(
            InputStream inputStream,
            Writer responseWriter
    ) throws IOException {

        String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        responseWriter.write("response = " + bodyText);

    }

    /**
     * <h2>HttpEntity 예시</h2>
     * <br>
     * <ul>
     *     <li>{@code HttpEntity}를 사용하면 {@link HttpMessageConverter}를 사용한다.</li>
     *     <li>
     *         Spring의 {@link HttpMessageConverter} 덕분에 간편하게 Request Data에 접근할 수 있다.
     *         <ol>
     *             <li>{@code HttpEntity}를 사용하면 {@link HttpMessageConverter}가 동작하여 자동으로 매핑된다.</li>
     *             <li>요청 뿐만이 아닌 응답까지 {@code HttpEntity} 하나만으로 사용이 가능해진다.</li>
     *         </ol>
     *     </li>
     * </ul>
     */
    @PostMapping("/v3/request-body-text")
    public HttpEntity<String> requestBodyTextV3(HttpEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();

        return new HttpEntity<>("response = " + body); // 매개변수 = Body Message
    }

    /**
     * <h2>HttpEntity란?</h2>
     * <p>{@link HttpEntity}는 HTTP Header, Body 정보를 편리하게 조회할 수 있도록 만들어준다.</p>
     *
     * <ul>
     *     <li>
     *         HttpEntity 역할
     *         <ol>
     *             <li>Http Request Body Message를 직접 조회한다.</li>
     *             <li>Request 뿐만 아니라 Response도 사용할 수 있도록 만들어준다.</li>
     *             <li>Response Header 또한 사용할 수 있다.</li>
     *             <li>Request Parameter를 조회하는 기능들과는 아무 관계가 없다.</li>
     *             <li>View를 반환하지 않는다.</li>
     *         </ol>
     *     </li>
     *     <li>
     *         HttpEntity를 상속받은 객체
     *         <ul>
     *             <li>{@code RequestEntity<>}: HTTP Request Method, URL 정보가 추가 되어있다.</li>
     *             <li>{@code ResponseEntity<>}: HTTP Response 상태 코드 설정이 가능하다.</li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @PostMapping("/v4/request-body-text")
    public HttpEntity<String> requestBodyTextV4(RequestEntity<String> httpEntity) {

        // HttpMessageConverter가 동작해서 아래 코드가 동작하게됨
        String body = httpEntity.getBody();
        // url, method 사용 가능

        return new ResponseEntity<>("response = " + body, HttpStatus.CREATED); // Body Data, 상태코드 201
    }

    /**
     * <h2>@RequestBody, @ResponseBody</h2>
     * <p>Spring에서 @RequestBody, @ResponseBody 어노테이션을 사용하면 각각 Request, Response 객체의 Body에 편하게 접근하여 사용할 수 있다.</p>
     *
     * <ul>
     *     <li>
     *         {@code @RequestBody}
     *         <ul>
     *             <li>요청 메세지 Body Data를 쉽게 조회할 수 있다.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         {@code @RequestHeader}
     *         <ul>
     *             <li>요청 헤더 정보 조회</li>
     *         </ul>
     *     </li>
     *     <li>
     *         {@code @ResponseBody}
     *         <ul>
     *             <li>응답 메세지 바디에 값을 쉽게 담아서 전달할 수 있도록 해준다.</li>
     *             <li>View가 아닌 <b>데이터를 반환</b>한다.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         <b>요약</b>
     *         <ol>
     *             <li>
     *                 요청 파라미터, HTML Form Data에 접근하는 경우
     *                 <ul>
     *                     <li>{@code @RequestParam}, {@code @ModelAttribute} 를 사용한다.</li>
     *                 </ul>
     *             </li>
     *             <li>
     *                 Http Message Body에 접근하는 경우
     *                 <ul>
     *                     <li>{@code @RequestBody} 를 사용한다. (<b>JSON</b>, XML, TEXT)</li>
     *                 </ul>
     *             </li>
     *         </ol>
     *     </li>
     * </ul>
     */
    @ResponseBody
    @PostMapping("/v5/request-body-text")
    public String requestBodyTextV5(
            @RequestBody String body,
            @RequestHeader HttpHeaders headers
    ) {
        return "request header = " + headers + " response body = " + body;
    }

}
