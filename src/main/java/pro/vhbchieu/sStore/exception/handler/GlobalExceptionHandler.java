package pro.vhbchieu.sStore.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import pro.vhbchieu.sStore.config.common.ApiResponse;
import pro.vhbchieu.sStore.config.common.DuplicateCode;
import pro.vhbchieu.sStore.exception.CustomException;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(500)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity.status(405).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(405)
                        .message("HTTP method not supported: " + e.getMethod())
                        .build()
        );
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("AccessDeniedException: {}", e.getMessage());
        return ResponseEntity.status(403).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(403)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResponse> handleNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        log.error("NoResourceFoundException: {}", e.getMessage());
        return ResponseEntity.status(404).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(404)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        return ResponseEntity.status(400).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(400)
                        .message(
                                Optional.ofNullable(e.getBindingResult().getFieldError())
                                        .map(FieldError::getDefaultMessage)
                                        .orElse("Validation error")
                        )
                        .build()
        );
    }

    @ExceptionHandler(value = CustomException.class)
    ResponseEntity<ApiResponse> handleCustomException(CustomException e, HttpServletRequest request) {
        log.error("CustomException: {}", e.getMessage());
        return ResponseEntity.status(400).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(400)
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage());
        return ResponseEntity.status(400).body(
                ApiResponse.builder()
                        .requestId(request.getRequestId())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI() + DuplicateCode.checkRequestQuery(request.getQueryString()))
                        .code(400)
                        .message("Malformed JSON request")
                        .build()
        );
    }


}
