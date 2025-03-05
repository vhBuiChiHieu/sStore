package pro.vhbchieu.sStore.sys.domain.dto.account;

import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.RoleRequest;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountUpdate {
    private AccountStatus status;
    private List<RoleRequest> roles = new ArrayList<>();
}
