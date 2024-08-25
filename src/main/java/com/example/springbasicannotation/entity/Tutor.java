package com.example.springbasicannotation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {

    private String name;
    private int age;

}
