package dyliang.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer money;
}
