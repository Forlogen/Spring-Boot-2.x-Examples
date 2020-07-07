package dyliang.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;
}
