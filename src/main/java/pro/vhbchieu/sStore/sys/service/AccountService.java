package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountChangePasswordDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountUpdate;

@Service
public interface AccountService {
    TokenResponse createAccount(AccountRequest request);

    AccountDto getInfo(AccountAuthDto accountAuthDto);

    void changePassword(AccountChangePasswordDto request, AccountAuthDto accountAuthDto);

    void update(Long id, AccountUpdate request);
}
