package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.config.constant.AccountStatus;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.config.constant.TokenType;
import pro.vhbchieu.sStore.config.security.JwtService;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.*;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.Role;
import pro.vhbchieu.sStore.sys.domain.entity.UserInfo;
import pro.vhbchieu.sStore.sys.repository.AccountRepository;
import pro.vhbchieu.sStore.sys.repository.RoleRepository;
import pro.vhbchieu.sStore.sys.repository.UserInfoRepository;
import pro.vhbchieu.sStore.sys.service.AccountService;
import pro.vhbchieu.sStore.sys.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByMail(request.getEmail()))
            throw new CustomException(ErrorContent.EMAIL_ALREADY_EXIST);

        List<Role> roles = request.getRoles().stream().map(rr ->
                roleRepository.findById(rr.getId()).orElseThrow(() -> new CustomException(ErrorContent.ROLE_NOT_EXIST))
        ).toList();

        Account newAccount = Account.builder()
                .mail(request.getEmail())
                .hashPassword(passwordEncoder.encode(request.getPassword()))
                .status(request.getStatus())
                .phone(request.getPhone())
                .roles(roles)
                .build();
        accountRepository.save(newAccount);

        UserInfo userInfo = UserInfo.builder()
                .account(newAccount)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();
        userInfoRepository.save(userInfo);

        return TokenResponse.builder()
                .token(jwtService.generateToken(newAccount.getId(), newAccount.getMail(), TokenType.ACCESS))
                .refreshToken(jwtService.generateToken(newAccount.getId(), newAccount.getMail(), TokenType.REFRESH))
                .build();
    }

    @Override
    public AccountDetailDto getInfo(Long userId) {
        if (userId == null) {
            AccountAuthDto accountAuthDto = SecurityUtils.getCurrentAccount();
            if (accountAuthDto == null) {
                throw new CustomException(ErrorContent.AUTHENTICATION_FAILED);
            }
            userId = accountAuthDto.getId();
        }

        Account account = accountRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );

        return new AccountDetailDto(account);
    }

    @Override
    public void changePassword(AccountChangePasswordDto request, AccountAuthDto accountAuthDto) {
        if (accountAuthDto == null) {
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
    public void changeStatus(Long id, Integer status) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );
        account.setStatus(AccountStatus.fromValue(status));
        accountRepository.save(account);
    }

    @Override
    public PageDto<AccountDto> getList(Integer pageIndex, Integer pageSize, String search, Integer status) {
        Specification<Account> specification = (r, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search != null) {
                predicates.add(cb.like(r.get("mail"), "%" + search + "%"));
            }

            if (status != null) {
              predicates.add(cb.equal(r.get("status"), AccountStatus.fromValue(status)));
          }

          return cb.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

        Page<AccountDto> accountDtoPage = accountRepository.findAll(specification, pageable).map(AccountDto::new);

        return PageDto.of(accountDtoPage);
    }

    @Override
    public void delete(Long userId) {
        accountRepository.deleteById(userId);
    }

    @Override
    public AccountStatsDto getStatic() {
        AccountStatsDto accountStatsDto = new AccountStatsDto();
        accountStatsDto.setTotalAccount(accountRepository.count());
        return accountStatsDto;
    }

    @Override
    public void update(Long accountId, AccountUpdate request) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new CustomException(ErrorContent.ACCOUNT_NOT_EXIST)
        );
        if (request.getStatus() != null) {
            account.setStatus(request.getStatus());
        }

        if (request.getRoles() != null && !request.getRoles().isEmpty() ) {
            List<Role> roles = request.getRoles().stream().map(roleRequest ->
                    roleRepository.findById(roleRequest.getId()).orElseThrow(() -> new CustomException(ErrorContent.ROLE_NOT_EXIST))
            ).toList();
            account.getRoles().clear();
            account.getRoles().addAll(roles);
        }

        accountRepository.save(account);
    }

}
