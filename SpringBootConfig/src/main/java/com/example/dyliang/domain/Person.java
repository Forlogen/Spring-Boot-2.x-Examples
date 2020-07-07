package com.example.dyliang.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")  // 将类中的所有属性和配置文件中相关的配置绑定，默认从全局配置文件中获取值
public class Person {

    @Getter
    @Setter
    private String last_name;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private Boolean boss;

    @Getter
    @Setter
    private Date birthday;

    @Getter
    @Setter
    private Map<String, Object> maps;

    @Getter
    @Setter
    private List<Object> lists;

    @Getter
    @Setter
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + last_name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birthday=" + birthday +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
