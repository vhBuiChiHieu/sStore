package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.Permission;
import pro.vhbchieu.sStore.sys.domain.entity.Role;

import java.util.*;

@Getter
public class AccountAuthDto implements UserDetails {

    private final Long id;
    private final String mail;
    private final String password;
    private final List<Permission> permissions;
    private final List<Role> roles;
    private final AccountStatus status;

    public AccountAuthDto(Account account) {
        this.id = account.getId();
        this.mail = account.getMail();
        this.password = account.getHashPassword();
        this.permissions = account.getRoles().stream().flatMap(role ->
                role.getPermissions().stream()
        ).toList();
        this.status = account.getStatus();
        this.roles = account.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isEnabled() {
        return status == AccountStatus.ACTIVE;
    }
}
