package pro.vhbchieu.sStore.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pro.vhbchieu.sStore.config.common.ApiResponse;
import pro.vhbchieu.sStore.config.common.DuplicateCode;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        DuplicateCode.response(response);
        response.getWriter().println(
                objectMapper.writeValueAsString(
                        ApiResponse.builder()
                                .requestId(request.getRequestId())
                                .path(request.getRequestURI() + "?" + request.getQueryString())
                                .code(HttpServletResponse.SC_FORBIDDEN)
                                .message(accessDeniedException.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                )
        );
        log.info("[REQUEST ID]: {}, [RESPONSE]: {}", request.getRequestId(), accessDeniedException.getMessage());
    }

}
