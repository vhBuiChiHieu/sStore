package pro.vhbchieu.sStore.sys.domain.dto.account;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountChangePasswordDto {
    @Pattern(
            regexp = "^(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least 1 special character")
    private String password;
    private String rePassword;
}
