package dyliang.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Account {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private Float money;

    @Getter
    @Setter
    @Column
    private String email;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Bank> banks;
}