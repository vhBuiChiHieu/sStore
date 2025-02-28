package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountChangePasswordDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;

@Service
public interface AccountService {
    TokenResponse createAccount(AccountRequest request);

    AccountDto getInfo(Long userId);

    void changePassword(AccountChangePasswordDto request, AccountAuthDto accountAuthDto);

    void changeStatus(Long id, @Min(0) @Max(2) Integer status);

    PageDto<AccountDto> getList(@Min(1) Integer pageIndex, @Min(1) Integer pageSize, Integer status);
}
