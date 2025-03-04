package pro.vhbchieu.sStore.sys.domain.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import pro.vhbchieu.sStore.sys.domain.entity.Account;
import pro.vhbchieu.sStore.sys.domain.entity.UserInfo;

import java.time.LocalDate;

@Getter
@Setter
public class AccountDetailDto extends AccountDto {

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String description;

    private String avatar;

    public AccountDetailDto(Account account, String... option) {
        super(account, option);
        UserInfo info = account.getUserInfo();
        if (info != null) {
            this.firstName = info.getFirstName();
            this.lastName = info.getLastName();
            this.dateOfBirth = info.getDateOfBirth();
            this.description = info.getDescription();
            this.avatar = info.getAvatar();
        }
    }
}
