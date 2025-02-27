package pro.vhbchieu.sStore.exception;

import pro.vhbchieu.sStore.config.constant.ErrorContent;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(ErrorContent errorContent) {
        super(errorContent.getContent());
    }

    public CustomException(ErrorContent errorContent, Throwable cause) {
        super(errorContent.getContent(), cause);
    }

}
