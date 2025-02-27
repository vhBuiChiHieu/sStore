package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountChangePasswordDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountUpdate;
import pro.vhbchieu.sStore.sys.service.AccountService;
import pro.vhbchieu.sStore.sys.utils.SecurityUtils;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    //admin create account
    @PreAuthorize("hasAuthority('ACCOUNT_CREATE')")
    @PostMapping()
    public TokenResponse createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE')")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid AccountUpdate request) {
        accountService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_OWN')")
    @PutMapping("/changePassword")
    public void changePassword(@RequestBody @Valid AccountChangePasswordDto request) {
        AccountAuthDto accountAuthDto = SecurityUtils.getCurrentAccount();
        accountService.changePassword(request, accountAuthDto);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ_OWN')")
    @GetMapping("/info")
    public AccountDto info() {
        AccountAuthDto accountAuthDto = SecurityUtils.getCurrentAccount();
        return accountService.getInfo(accountAuthDto);
    }

}
