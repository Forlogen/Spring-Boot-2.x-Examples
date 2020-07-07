package dyliang.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;
}
