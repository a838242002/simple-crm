package com.example.crm.dto.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BasicResponse {
    String code;

    String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object data;

    public static BasicResponse success(Object data) {
        return BasicResponse.builder()
                .data(data)
                .code(MessageCode.success.getCode())
                .status(MessageCode.success.getStatus())
                .build();
    }

    public static BasicResponse success() {
        return BasicResponse.builder()
                .code(MessageCode.success.getCode())
                .status(MessageCode.success.getStatus())
                .build();
    }

    public static BasicResponse fail(String message) {
        return BasicResponse.builder()
                .code(MessageCode.fail.getCode())
                .status(MessageCode.fail.getStatus())
                .message(message)
                .build();
    }
}
