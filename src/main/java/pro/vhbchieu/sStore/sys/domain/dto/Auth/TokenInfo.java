package pro.vhbchieu.sStore.sys.domain.dto.Auth;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenInfo {
    private Long id;
    private String username;
    private String type;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private boolean isExpired;
}