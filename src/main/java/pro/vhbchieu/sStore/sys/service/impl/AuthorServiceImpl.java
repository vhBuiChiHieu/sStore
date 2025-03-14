package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.config.constant.TokenType;
import pro.vhbchieu.sStore.config.security.JwtService;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.*;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.UserInfo;
import pro.vhbchieu.sStore.sys.repository.AccountRepository;
import pro.vhbchieu.sStore.sys.repository.RoleRepository;
import pro.vhbchieu.sStore.sys.repository.UserInfoRepository;
import pro.vhbchieu.sStore.sys.service.AuthorService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterDto request) {
        if (accountRepository.existsByMail(request.getEmail()))
            throw new CustomException(ErrorContent.EMAIL_ALREADY_EXIST);

        if (!request.getPassword().equals(request.getRePassword()))
            throw new CustomException(ErrorContent.RE_PASSWORD_NOT_MATCH);

        Account newAccount = Account.builder()
                .mail(request.getEmail())
                .hashPassword(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(roleRepository.findByName("USER")))
                .status(AccountStatus.ACTIVE)
                .build();
        accountRepository.save(newAccount);

        UserInfo userInfo = UserInfo.builder()
                .account(newAccount)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();
        userInfoRepository.save(userInfo);

    }

    @Override
    public TokenResponse login(LoginDto request) {
        Account account = accountRepository.findByMail(request.getEmail()).orElseThrow(
                () -> new CustomException(ErrorContent.EMAIL_NOT_EXIST)
        );

        if (!passwordEncoder.matches(request.getPassword(), account.getHashPassword()))
            throw new CustomException(ErrorContent.PASSWORD_NOT_MATCH);

        if (account.getStatus() != AccountStatus.ACTIVE)
            throw new CustomException(ErrorContent.ACCOUNT_INACTIVE);

        return TokenResponse.builder()
                .token(jwtService.generateToken(account.getId(), account.getMail(), TokenType.ACCESS))
                .refreshToken(
                        (request.getRefresh() != null && request.getRefresh()) ?
                        jwtService.generateToken(account.getId(), account.getMail(), TokenType.REFRESH) : null
                )
                .build();
    }

    @Override
    public TokenResponse refresh(RefreshDto request) {
        TokenInfo tokenInfo = jwtService.getTokenInfo(request.getRefreshToken());
        if (!tokenInfo.getType().equals(TokenType.REFRESH.name()))
            throw new CustomException(ErrorContent.TOKEN_INVALID);
        return TokenResponse.builder()
                .token(jwtService.generateToken(tokenInfo.getId(), tokenInfo.getUsername(), TokenType.ACCESS))
                .build();
    }

}
