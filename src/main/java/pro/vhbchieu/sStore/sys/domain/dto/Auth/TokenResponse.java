package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
    private String token;
    private String refreshToken;
}
