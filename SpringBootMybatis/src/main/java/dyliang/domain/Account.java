package dyliang.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Float money;
}
