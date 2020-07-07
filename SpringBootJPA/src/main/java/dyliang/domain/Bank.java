package dyliang.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_bank")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Bank {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column
    private String address;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "banks")
    private List<Account> accounts;
}
