package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Tutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <h2>JSON</h2>
 *
 * <p>JSON은 @RestController 에서 가장 많이 사용되는 데이터 형식이다.</p>
 * <p>현재 대부분의 API는 Request, Response 모두 JSON형태로 통신한다.</p>
 * <p>JSON 형태로 Data를 전송할 때 Request Header의 content-type이 꼭 <b>application/json</b> 이여야 한다.</p>
 */
@RestController
public class JsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * <h2>HttpServletRequest 사용</h2>
     *
     * <ul>
     *     <li>
     *         Postman
     *         <ul>
     *             <li>Content-type 헤더 확인: application/json</li>
     *         </ul>
     *     </li>
     *     <li>HttpServletRequest를 사용하여 HTTP Message Body 데이터를 Read하여 문자로 변환한다.</li>
     *     <li>문자로 만들어진 JSON을 Jackson 라이브러리의 {@code objectMapper}를 사용하여 Object로 변환</li>
     * </ul>
     */
    @PostMapping("/v1/request-body-json")
    public void requestBodyJsonV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        // request body message를 Read
        ServletInputStream inputStream = request.getInputStream();
        // UTF-8 형식의 String으로 변환한다.
        String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // String requestBody를 ObjectMapper를 사용하여 변환 "{\"name\":\"wonuk\", \"age\":10}"
        Tutor tutor = objectMapper.readValue(requestBody, Tutor.class);

        response.getWriter().write("tutor" + tutor);
    }

    /**
     * <h2>@RequestBody 사용</h2>
     *
     * <ul>
     *     <li>{@code @RequestBody}를 사용하여 HTTP Request Body의 Data에 접근한다.</li>
     * </ul>
     */
    @PostMapping("/v2/request-body-json")
    public String requesBodyJsonV2(@RequestBody String requestBody) throws IOException {

        Tutor tutor = objectMapper.readValue(requestBody, Tutor.class);

        return "tutor.getName() = " + tutor.getName() + " tutor.getAge() = " + tutor.getAge();
    }

    /**
     * <h2>ObjectMapper 제거</h2>
     *
     * <ul>
     *     <li>
     *         {@code @RequestBody}
     *         <ul>
     *             <li>{@code RequestBody} 어노테이션을 사용하면 <b>Object를 Mapping</b>할 수 있다.</li>
     *             <li>{@code HttpEntity<>}, {@code @RequestBody}를 사용하면 <b>HTTPMessageConverter</b>가 RequestBody의 Data를 개발자가 원하는 String이나 Object로 변환해준다.</li>
     *             <li>
     *                 JSON to Object의 Mapping 또한 가능하다.
     *                 <ul>
     *                     <li>
     *                         <p>{@code MappingJackson2HttpMessageConverter}의 역할</p>
     *                         <p>쉽게 설명하면 HTTP Message Converter가 ObjectMapper를 대신 실행한다.</p>
     *                     </li>
     *                 </ul>
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @PostMapping("/v3/request-body-json")
    public String requestBodyJsonV3(@RequestBody Tutor tutor) {

        Tutor requestBodyTutor = tutor;

        return "tutor = " + requestBodyTutor;
    }

    /**
     * <h2>@RequestBody는 생략할 수 없다.</h2>
     * <ul>
     *     <li>
     *         생략하면 {@code ModelAttribute}가 된다.
     *         <ul>
     *             <li>요청 파라미터를 처리하도록 설정된다.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         Request Header의 contentType은 꼭 application/json 이여야 한다.
     *         <ul>
     *             <li>위 설정 정보를 기반으로 MessageConverter가 실행된다.</li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @PostMapping("/v4/request-body-json")
    public String requestBodyJsonV4(Tutor tutor) { // @RequestBody 생략시 @ModelAttribute가 된다.

        Tutor requestBodyTutor = tutor;

        // getName(): null 반환
        // getAge(): 0 반환
        return "tutor.getName() = " + requestBodyTutor.getName() + " tutor.getAge() = " + requestBodyTutor.getAge();
    }

    /**
     * <h2>HttpEntity 사용</h2>
     * <ul>
     *     <li>
     *         {@code HttpEntity<Tutor>}
     *         <ul>
     *             <li>Generic Type으로 Tutor가 지정되어 있기 때문에 해당 Class로 반환된다.</li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @PostMapping("/v5/request-body-json")
    public String requestBodyJsonV5(
            HttpEntity<Tutor> httpEntity
    ) {
        // 값을 꺼내서 사용해야한다!
        Tutor tutor = httpEntity.getBody();

        return "tutor.getName() = " + tutor.getName() + " tutor.getAge() = " + tutor.getAge();
    }

    // @RestController = @Controller + @ResponseBody
    @PostMapping("/v6/request-body-json")
    public Tutor requestJson(@RequestBody Tutor tutor) {
        return tutor;
    }

}
