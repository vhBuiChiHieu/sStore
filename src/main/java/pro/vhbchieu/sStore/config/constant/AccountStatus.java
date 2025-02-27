package pro.vhbchieu.sStore.config.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatus {
    ACTIVE(0),
    INACTIVE(1),
    LOCKED(2),
    ;

    private final int value;

    AccountStatus(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static AccountStatus fromValue(int value) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
