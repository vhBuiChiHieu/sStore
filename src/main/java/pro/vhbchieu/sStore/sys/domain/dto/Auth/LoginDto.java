package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String email;
    private String password;
    private Boolean refresh;

}
