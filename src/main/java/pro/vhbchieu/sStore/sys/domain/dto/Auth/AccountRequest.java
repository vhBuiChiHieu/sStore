package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.vhbchieu.sStore.config.constant.AccountStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountRequest {

    @NotBlank(message = "Email must not be blank")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least 1 special character")
    private String password;
    private String rePassword;

    private AccountStatus status = AccountStatus.ACTIVE;

    private List<RoleRequest> roles = new ArrayList<>();

    //
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfBirth;
}
