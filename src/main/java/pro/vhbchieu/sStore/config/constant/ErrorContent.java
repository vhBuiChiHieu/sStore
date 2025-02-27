package pro.vhbchieu.sStore.config.constant;

import lombok.Getter;

@Getter
public enum ErrorContent {
    EMAIL_ALREADY_EXIST("Email already exist"),
    EMAIL_NOT_EXIST("Email not exist"),
    ACCOUNT_NOT_EXIST("Account not exist"),
    ACCOUNT_INACTIVE("Account inactive"),
    ACCOUNT_STATUS_INVALID("Account status invalid"),
    AUTHENTICATION_FAILED("Authentication failed"),
    TOKEN_INVALID("Token invalid"),
    LOGIN_WITH_FRESH_TOKEN("Cannot login with fresh token"),
    RE_PASSWORD_NOT_MATCH("Re-password not match"),
    PASSWORD_NOT_MATCH("Password not match"),
    ;
    private final String content;

    ErrorContent(String content) {
        this.content = content;
    }
}
