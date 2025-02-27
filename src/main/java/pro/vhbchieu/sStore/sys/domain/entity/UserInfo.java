package pro.vhbchieu.sStore.sys.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String description;
    private LocalDate dateOfBirth;
    private String avatar;

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "account_id")
    private Account account;
}
