package com.example.dyliang.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dog {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
