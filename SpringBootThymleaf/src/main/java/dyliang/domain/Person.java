package dyliang.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;
}
