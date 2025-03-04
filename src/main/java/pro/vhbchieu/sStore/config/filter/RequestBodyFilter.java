package pro.vhbchieu.sStore.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class RequestBodyFilter extends RequestBodyAdviceAdapter {

    private final HttpServletRequest httpRequest;
    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(
            @NonNull MethodParameter methodParameter,
            @NonNull Type targetType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    @NonNull
    public Object afterBodyRead(
            @NonNull Object body,
            @NonNull HttpInputMessage inputMessage,
            @NonNull MethodParameter parameter,
            @NonNull Type targetType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        String requestBodyInfo = "[REQUEST_ID]: " + httpRequest.getRequestId() + ", [REQUEST_BODY]: " + objectMapper.writeValueAsString(body);
        log.info(requestBodyInfo);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

}
