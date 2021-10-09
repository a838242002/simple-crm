package com.example.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BizException extends RuntimeException{
    public BizException(String message) {
        super(message);

    }
}
