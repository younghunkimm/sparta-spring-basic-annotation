package com.example.springbasicannotation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class RequestParamControllerV2 {

    @ResponseBody
    @GetMapping("/v1/request-param")
    public String requestParamV1(
            @RequestParam("name") String userName,
            @RequestParam("age") int userAge
    ) {
        // logic
        log.info("name={}", userName);
        log.info("age={}", userAge);
        return "success";
    }

    /**
     * <p>{@code GET} http://localhost:8080/v2/request-param?name=sparta&age=100</p>
     * <p>"속성값"과 변수명이 같으면 생략이 가능하다</p>
     * @param name
     * @param age
     */
    @ResponseBody
    @GetMapping("/v2/request-param")
    public String requestParamV2(
            @RequestParam String name,
            @RequestParam int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    /**
     * <h2>{@code @RequestParam} 사용법</h2>
     * <p>{@code GET} http://localhost:8080/v3/request-param?name=sparta&age=10</p>
     * <br>
     * <h4>어노테이션, 속성값 모두 생략</h4>
     * <p><b>❗ 권장되지 않는 방식. 협업 시 다른 개발자들에게 혼동</b></p>
     * <ul>
     *     <li>{@code @RequestParam}은 생략이 가능하다.</li>
     *     <li>생략하면 {@code @requestParam(required=false)} 필수 여부 속성이 {@code default}로 설정된다.</li>
     *     <li>단, 요청 파라미터와 이름이 완전히 같아야 한다.</li>
     *     <li>단순 타입({@code int}, {@code String}, {@code Integer} 등) 이어야 한다.</li>
     * </ul>
     */
    @ResponseBody
    @GetMapping("/v3/request-param")
    public String requestParamV3(
            String name,
            int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    /**
     * <p>{@code GET} http://localhost:8080/v4/request-param?name=sparta&age=10</p>
     * <br>
     * <h4>{@code required} 속성 설정</h4>
     * <ul>
     *     <li>파라미터의 필수 값을 설정한다.</li>
     *     <li>API 스펙을 규정할 때 사용한다.</li>
     *     <li>
     *         {@code @RequestParam}을 사용하면 기본 Default값은 {@code True}이다
     *         <ul>
     *             <li>{@code True}로 설정된 파라미터 값이 요청에 존재하지 않으면 {@code 400 BadRequest}(클라이언트 측 에러)</li>
     *         </ul>
     *     </li>
     *     <li>
     *         {@code required = false} 설정이 되어있으면 해당 파라미터는 없어도 된다.
     *         <ul>
     *             <li>
     *                 <b>주의!</b> {@code http://localhost:8080/v4/request-param?name=sparta} 요청한다면?
     *                 <ul>
     *                     <li>{@code 500 Error}가 발생한다.</li>
     *                     <li>
     *                         {@code int Type}에는 {@code null}을 넣을 수 없다. <b>0이라도 들어가야 한다.</b>
     *                         <pre>{@code
     *                         int a = null; // 💥 에러 발생
     *                         Integer b = null;
     *                         }</pre>
     *                     </li>
     *                 </ul>
     *             </li>
     *             <li>따라서 보통 {@code null}을 허용하는 <b>{@code Integer}</b>로 사용하거나 <b>{@code default} 옵션</b>을 사용한다.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         파라미터 {@code key}값만 있고, {@code Value}가 없는 경우<br>
     *         {@code http://localhost:8080/request-param?name=}
     *         <ul>
     *             <li>{@code null}과 빈 문자열 {@code ""}은 다르다!</li>
     *         </ul>
     *     </li>
     * </ul>
     */
    @ResponseBody
    @GetMapping("/v4/request-param")
    public String requestParamV4(
            @RequestParam(required = true) String name, // 필수
//            @RequestParam(required = false) int age	// 필수가 아님
            @RequestParam(required = false) Integer age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    /**
     * <p>{@code GET} http://localhost:8080/v5/request-param?name=sparta&age=10</p>
     * <br>
     * <h4>{@code default} 속성 적용</h4>
     * <ul>
     *     <li>파라미터의 기본 값을 설정한다.</li>
     *     <li>
     *         <p>name Parameter 의 값이 없으면 기본적으로 “sparta”으로 설정한다.</p>
     *         <p>Ex1) {@code http://localhost:8080/v5/request-param?age=100}</p>
     *         <p>Ex2) {@code http://localhost:8080/v5/request-param}</p>
     *     </li>
     *     <li>
     *         <p><b>주의!</b> {@code defaultValue} 속성을 설정하게 되면 {@code ""} 빈 문자열의 경우에도 기본값이 설정된다.</p>
     *         <p>Ex) {@code http://localhost:8080/v5/request-param?name&age}</p>
     *     </li>
     * </ul>
     */
    @ResponseBody
    @GetMapping("/v5/request-param")
    public String requestParamV5(
            @RequestParam(required = true, defaultValue = "sparta") String name,
            @RequestParam(required = false, defaultValue = "1") int age
    ) {
        // logic
        log.info("name={}", name);
        log.info("age={}", age);
        return "success";
    }

    @ResponseBody
    @GetMapping("/v6/request-param")
    public String requestParamV6(
//            @RequestParam Map<String, String> map
            @RequestParam MultiValueMap<String, String> map
    ) {
        // logic

        log.info("name={}", map.get("name"));
        log.info("age={}", map.get("age"));

        return "success";
    }

}
