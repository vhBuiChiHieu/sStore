package pro.vhbchieu.sStore.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(-101)
@Slf4j
public class RequestFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestId = request.getRequestId();
        String requestInfo = "[REQUEST_ID]: " + requestId +
                ", [IP]: " + getClientIp(request) +
                ", [METHOD]: " + request.getMethod() +
                ", [URI]: " + request.getRequestURI() +
                ", [QUERY]: " + request.getQueryString();
        log.info(requestInfo);

        filterChain.doFilter(servletRequest, servletResponse);

        String responseInfo = "[REQUEST_ID]: "+ requestId + ", [RESPONSE_TIME]: " + (System.currentTimeMillis() - startTime) + "ms";
        log.info(responseInfo);
    }

    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-FORWARDED-FOR");
        if (clientIp != null && !clientIp.isEmpty()) {
            clientIp = request.getHeader("X-Real-IP");
        }
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
