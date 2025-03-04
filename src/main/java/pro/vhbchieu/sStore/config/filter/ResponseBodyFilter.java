package pro.vhbchieu.sStore.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pro.vhbchieu.sStore.config.common.ApiResponse;
import pro.vhbchieu.sStore.config.common.DuplicateCode;
import org.springframework.lang.NonNull;
import pro.vhbchieu.sStore.sys.controller.FileController;


import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ResponseBodyFilter implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest httpRequest;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        String path = httpRequest.getRequestURI();
        return !(path.contains("/v3/api-docs") || path.contains("/swagger-ui"));
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        if (!returnType.getContainingClass().isAnnotationPresent(RestController.class) || returnType.getContainingClass().equals(FileController.class))
            return body;
        if (body == null)
            body = new Object();

        log.info("[REQUEST ID]: {}, [RESPONSE]: {}", httpRequest.getRequestId(), objectMapper.writeValueAsString(body));

        if (body.getClass() != ApiResponse.class && body.getClass() != String.class){

            try {
                body = ApiResponse.builder()
                        .requestId(httpRequest.getRequestId())
                        .path(httpRequest.getRequestURI() + DuplicateCode.checkRequestQuery(httpRequest.getQueryString()))
                        .timestamp(LocalDateTime.now())
                        .code(200)
                        .message("Success")
                        .data(body)
                        .build();

            } catch (Exception e) {
                return body;
            }
        }
        return body;
    }

}
