package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.config.constant.TokenType;
import pro.vhbchieu.sStore.config.security.JwtService;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountChangePasswordDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountDto;
import pro.vhbchieu.sStore.sys.domain.dto.account.AccountUpdate;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.Role;
import pro.vhbchieu.sStore.sys.repository.AccountRepository;
import pro.vhbchieu.sStore.sys.repository.RoleRepository;
import pro.vhbchieu.sStore.sys.service.AccountService;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByMail(request.getEmail()))
            throw new CustomException(ErrorContent.EMAIL_ALREADY_EXIST);

        List<Role> roles = request.getRoles().stream().map(roleRepository::findByName).toList();

        Account newAccount = Account.builder()
                .mail(request.getEmail())
                .hashPassword(passwordEncoder.encode(request.getPassword()))
                .status(AccountStatus.ACTIVE)
                .roles(roles)
                .build();
        accountRepository.save(newAccount);

        return TokenResponse.builder()
                .token(jwtService.generateToken(newAccount.getId(), newAccount.getMail(), TokenType.ACCESS))
                .refreshToken(jwtService.generateToken(newAccount.getId(), newAccount.getMail(), TokenType.REFRESH))
                .build();
    }

    @Override
    public AccountDto getInfo(AccountAuthDto accountAuthDto) {
        if (accountAuthDto == null){
            throw new CustomException(ErrorContent.AUTHENTICATION_FAILED);
        }
        log.info("Get account info for {}", accountAuthDto.getUsername());
        Account account = accountRepository.findByMail(accountAuthDto.getUsername()).orElseThrow(
                () -> new CustomException(ErrorContent.EMAIL_NOT_EXIST)
        );
        return new AccountDto(account);
    }

    @Override
    public void changePassword(AccountChangePasswordDto request, AccountAuthDto accountAuthDto) {
        if (accountAuthDto == null){
            throw new CustomException(ErrorContent.AUTHENTICATION_FAILED);
        }
        Account account = accountRepository.findById(accountAuthDto.getId()).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );

        if (!request.getPassword().equals(request.getRePassword()))
            throw new CustomException(ErrorContent.RE_PASSWORD_NOT_MATCH);

        account.setHashPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void update(Long id, AccountUpdate request) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );
        if (request.getStatus() == null)
            request.setStatus(AccountStatus.LOCKED);
        else
            account.setStatus(request.getStatus());

        if (request.getPassword() != null)
            account.setHashPassword(passwordEncoder.encode(request.getPassword()));

        accountRepository.save(account);
    }
}
