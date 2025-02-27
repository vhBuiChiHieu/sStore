package pro.vhbchieu.sStore.sys.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountChangePasswordDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;
import pro.vhbchieu.sStore.sys.service.AccountService;
import pro.vhbchieu.sStore.sys.service.AuthorService;
import pro.vhbchieu.sStore.sys.utils.SecurityUtils;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final AuthorService authorService;

    //admin create account
    @PreAuthorize("hasAuthority('ACCOUNT_CREATE')")
    @PostMapping()
    public TokenResponse createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_OWN')")
    @PutMapping("/change-password")
    public void changePassword(@RequestBody @Valid AccountChangePasswordDto request) {
        AccountAuthDto accountAuthDto = SecurityUtils.getCurrentAccount();
        accountService.changePassword(request, accountAuthDto);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE')")
    @PutMapping("/change-status/{id}")
    public void changeStatus(
            @PathVariable("id") Long id,
            @Min (0) @Max (2) @RequestParam(value = "status") Integer status
    ) {
        accountService.changeStatus(id, status);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ_OWN')")
    @GetMapping("/profile")
    public AccountDto info() {
        AccountAuthDto accountAuthDto = SecurityUtils.getCurrentAccount();
        return accountService.getInfo(accountAuthDto);
    }

}
