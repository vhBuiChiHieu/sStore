package pro.vhbchieu.sStore.sys.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;

public class SecurityUtils {

    SecurityUtils() {}

    public static AccountAuthDto getCurrentAccount() {
        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AccountAuthDto accountAuthDto) {
            return accountAuthDto;
        }
        return null;
    }

}
