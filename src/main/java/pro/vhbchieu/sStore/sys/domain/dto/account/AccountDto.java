package pro.vhbchieu.sStore.sys.domain.dto.account;

import lombok.*;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.sys.domain.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {
    protected Long id;
    protected String mail;
    protected AccountStatus status;
    protected String phone;
    protected List<RoleDto> roles;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public AccountDto(Account account, String... option) {
        this.id = account.getId();
        this.mail = account.getMail();
        this.status = account.getStatus();
        if (option.length == 0)
            this.roles = account.getRoles().stream().map(RoleDto::new).toList();
        this.phone = account.getPhone();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }
}
