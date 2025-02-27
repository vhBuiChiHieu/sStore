package pro.vhbchieu.sStore.sys.service;

import org.springframework.stereotype.Service;
import pro.vhbchieu.sStore.sys.domain.dto.Auth.*;

@Service
public interface AuthorService {

    void register(RegisterDto request);

    TokenResponse login(LoginDto request);

    TokenInfo checkToken(String token);

    TokenResponse refresh(RefreshDto request);

}
