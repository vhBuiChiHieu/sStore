package pro.vhbchieu.sStore.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.config.constant.TokenType;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.TokenInfo;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
@RequiredArgsConstructor
public class JwtService {

    private String secret;
    private Long expiration;

    public String generateToken(Long id, String username, TokenType type) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        Date now = new Date();
        if (type == TokenType.REFRESH)
            expiration = 1000L * 60 * 60 * 24 * 30;
        Date expirationDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .claim("id", id)
                .claim("type", type.name())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
             return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            throw new CustomException(ErrorContent.TOKEN_INVALID);
        }
    }

    public TokenInfo getTokenInfo(String token) {
        Jws<Claims> claimsJws = validateToken(token);
        Claims claims = claimsJws.getPayload();
        return TokenInfo.builder()
                .id(claims.get("id", Long.class))
                .username(claims.getSubject())
                .type(claims.get("type", String.class))
                .issuedAt(claims.getIssuedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .expiration(claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .isExpired(claims.getExpiration().before(new Date()))
                .build();
    }
}
