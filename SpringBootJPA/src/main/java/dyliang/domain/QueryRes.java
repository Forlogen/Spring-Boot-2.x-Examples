package dyliang.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QueryRes {

    @Getter
    @Setter
    private Float money;

    @Getter
    @Setter
    private String address;
}
