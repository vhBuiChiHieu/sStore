package pro.vhbchieu.sStore.config.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String requestId;
    private Integer code;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    private Object data;
}
