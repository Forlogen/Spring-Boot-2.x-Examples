package dyliang.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Person {

    @Getter
    @Setter
    private Integer id;
    private String name;
}
