package pro.vhbchieu.sStore.sys.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.common.PageDto;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountAuthDto;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.AccountRequest;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenResponse;
import pro.vhbchieu.sStore.sys.domain.dto.account.*;

@Service
public interface AccountService {

    TokenResponse createAccount(AccountRequest request);

    /**
     * Get account information by user ID
     *
     * @param userId The ID of the user to retrieve information for
     * @return AccountDetailDto containing user account details
     * @throws CustomException if user is not found
     */
    AccountDetailDto getInfo(Long userId);

    /**
     * Change password for authenticated user
     *
     * @param request Password change request containing old and new passwords
     * @param accountAuthDto Authentication details of the current user
     * @throws CustomException if old password is incorrect or validation fails
     */
    void changePassword(AccountChangePasswordDto request, AccountAuthDto accountAuthDto);

    /**
     * Change account status
     *
     * @param id ID of the account to update
     * @param status New status value (0-2)
     * @throws CustomException if account is not found or status is invalid
     */
    void changeStatus(Long id, @Min(0) @Max(2) Integer status);

    /**
     * Get paginated list of accounts
     *
     * @param pageIndex Page number (starting from 1)
     * @param pageSize Number of items per page
     * @param search Mail off account
     * @param status Filter by account status (optional)
     * @return PageDto containing list of AccountDto objects
     */
    PageDto<AccountDto> getList(@Min(1) Integer pageIndex, @Min(1) Integer pageSize, String search, Integer status);

    void delete(Long userId);

    AccountStatsDto getStatic();

    void update(Long accountId, AccountUpdate request);
}
