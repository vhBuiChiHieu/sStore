package pro.vhbchieu.sStore.config.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.repository.AccountRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Account account = accountRepository.findByMail(mail).orElseThrow(
                () -> new CustomException(ErrorContent.EMAIL_NOT_EXIST)
        );
        return new AccountAuthDto(account);
    }
}
