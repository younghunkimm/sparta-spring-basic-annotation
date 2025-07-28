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
