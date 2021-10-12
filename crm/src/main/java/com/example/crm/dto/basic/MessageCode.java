package com.example.crm.dto.basic;

import lombok.Getter;

@Getter
public enum MessageCode {
    success("0000", "SUCCESS"),
    fail("0001", "FAIL");

    private final String code;
    private final String status;

    MessageCode(String code, String status) {
        this.code = code;
        this.status = status;
    }
}
