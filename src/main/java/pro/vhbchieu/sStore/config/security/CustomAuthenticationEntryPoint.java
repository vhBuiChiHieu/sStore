package pro.vhbchieu.sStore.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pro.vhbchieu.sStore.config.common.ApiResponse;
import pro.vhbchieu.sStore.config.common.DuplicateCode;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        DuplicateCode.response(response);
        response.getWriter().println(
                objectMapper.writeValueAsString(
                        ApiResponse.builder()
                                .requestId(request.getRequestId())
                                .path(request.getRequestURI() + "?" + request.getQueryString())
                                .code(HttpServletResponse.SC_UNAUTHORIZED)
                                .message(authException.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                )
        );
        log.info("[REQUEST ID]: {}, [RESPONSE]: {}", request.getRequestId(), authException.getMessage());
    }

}
