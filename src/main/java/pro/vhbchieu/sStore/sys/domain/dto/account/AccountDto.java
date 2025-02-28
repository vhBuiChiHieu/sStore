package pro.vhbchieu.sStore.sys.domain.dto.account;

import lombok.*;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.sys.domain.entity.Account;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String mail;
    private AccountStatus status;
    private List<RoleDto> roles;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.mail = account.getMail();
        this.status = account.getStatus();
        this.roles = account.getRoles().stream().map(RoleDto::new).toList();
    }
}
