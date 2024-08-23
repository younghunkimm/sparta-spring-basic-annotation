package com.example.springbasicannotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 @RequestMapping("/posts/{postId}")
//@RequestMapping("/posts")
@RestController
public class PathVariableController {
    // postId로 된 post 단건 조회
//    @GetMapping("/{postId}")
//    public String pathVariableV1(@PathVariable("postId") Long data) {
//        // logic
//        String result = "PathvariableV1 결과입니다 : " + data;
//        return result;
//    }

    // 변수명과 같다면 속성값 생략가능
//    @GetMapping("/{postId}")
//    public String pathVariableV2(@PathVariable Long postId) {
//        // logic
//        String result = "PathvariableV2 결과입니다 : " + postId;
//        return result;
//    }

//    @GetMapping("/{postId}/comments/{commentId}")
//    public String pathVariableV3(
//            @PathVariable Long postId,
//            @PathVariable Long commentId
//    ) {
//        // logic
//        String result = "PathvariableV3 결과입니다 postId : " + postId + "commentsId : " + commentId;
//        return result;
//    }
    @GetMapping("/comments/{commentId}")
    public String pathVariableV4(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        // logic
        String result = "PathvariableV4 결과입니다 postId : " + postId + "commentsId : " + commentId;
        return result;
    }

}
