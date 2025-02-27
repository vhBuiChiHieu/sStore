package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDto {

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Invalid email format"
    )
    private String email;

    @Pattern(
            regexp = "^(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least 1 special character"
    )
    private String password;
    private String rePassword;

    private LocalDate dateOfBirth;

    @Pattern(
            regexp = "(^((\\+84|84|0)[3|5|7|8|9])[0-9]{8}$)",
            message = "Invalid Vietnam phone number format"
    )
    private String phone;

}
