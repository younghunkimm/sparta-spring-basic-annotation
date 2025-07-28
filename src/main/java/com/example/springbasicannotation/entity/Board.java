package com.example.springbasicannotation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 모든 필드를 가진 생성자
@NoArgsConstructor // 기본 생성자를 생성해주는 Lombok Annotation
public class Board {

    private String title;
    private String content;
}
