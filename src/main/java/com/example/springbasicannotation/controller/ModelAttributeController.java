package com.example.springbasicannotation.controller;

import com.example.springbasicannotation.entity.Tutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <h2>{@code @ModelAttribute}</h2>
 * <p>요청 파라미터를 받아 필요한 {@code Object}로 바인딩 해준다.</p>
 * <p>주로 {@code HTML} 폼에서 전송된 데이터를 바인딩 하고 {@code HTTP Method POST}인 경우 사용된다.</p>
 */
@Controller
public class ModelAttributeController {

    /**
     * <h2>기존 코드</h2>
     *
     * <ul>
     *     <li>
     *         {@code @RequestParam} 의 {@code Mapping} 을 사용하게 되면 아래와 같은 객체를 생성하는 코드가 포함된다.
     *         <ul>
     *             <li><b>{@code @ModelAttribute}</b> 는 해당 과정을 자동화 한다.</li>
     *         </ul>
     *     </li>
     *     <li>
     *         Postman
     *         <pre>{@code
     *         POST /v1/tutor
     *         content-type: application/x-www-form-urlencoded
     *
     *         name=wonuk&age=100
     *         }</pre>
     *     </li>
     * </ul>
     */
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

    /**
     * <h2>{@code @ModelAttribute} 적용</h2>
     *
     * <ul>
     *     <li>
     *         Postman
     *         <pre>{@code
     *         POST /v2/tutor
     *         content-type: application/x-www-form-urlencoded
     *
     *         name=wonuk&age=100
     *         }</pre>
     *     </li>
     *     <li>
     *         {@code @ModelAttribute} 동작 순서
     *         <ol>
     *             <li>파라미터에 {@code @ModelAttribute}가 있으면 파라미터인 {@code Tutor} 객체를 생성한다.</li>
     *             <li>
     *                 요청 파라미터 이름으로 객체 필드의 {@code Setter}를 호출해서 바인딩한다.
     *                 <ol style="list-style-type: lower-alpha">
     *                     <li>파라미터 이름이 {@code name} 이면 {@code setName(value);} 메서드를 호출한다.</li>
     *                     <li>파라미터 이름과 필드 이름이 반드시 같아야 한다!</li>
     *                 </ol>
     *             </li>
     *         </ol>
     *     </li>
     *     <li>
     *         {@code Setter}가 없다면?
     *         <ul>
     *             <li>
     *                 <b>객체 필드에 값이 set되지 않는다.</b>
     *                 <pre>{@code
     *                 tutor name = null age = 0
     *                 }</pre>
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         파라미터의 타입이 다른 경우
     *         <ul>
     *             <li>
     *                 <p>만약 요청 파라미터 {@code age}에 {@code int}가 아닌 {@code String}이 전달된다면?</p>
     *                 <p>Ex) {@code http://localhost:8080/v2/tutor} + x-www-form-urlencoded</p>
     *                 <pre>{@code
     *                 POST /v2/tutor
     *                 content-type: application/x-www-form-urlencoded
     *
     *                 name=wonuk&age=nbcamp
     *                 }</pre>
     *
     *                 <ul>
     *                     <li>BindException 발생</li>
     *                     <li>이런 경우 때문에 <b>Validation(검증)</b>이 필요하다</li>
     *                 </ul>
     *             </li>
     *         </ul>
     *     </li>
     * </ul>
     */
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
