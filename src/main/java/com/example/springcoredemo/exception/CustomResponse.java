package com.example.springcoredemo.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.springcoredemo.enums.MessageErrorKey;
import com.example.springcoredemo.enums.MessageKey;

public class CustomResponse extends RuntimeException {
    private Integer statusCode;
    private String messageKey;
    private List<?> data;

    public CustomResponse(Integer statusCode, MessageKey messageKey, List<?> data) {
        super(messageKey.getMessageKey());
        this.messageKey = messageKey.getMessageKey();
        this.statusCode = statusCode;
        this.data = data;
    }

    public CustomResponse(HttpStatus status, MessageKey messageKey, List<?> data) {
        super(messageKey.getMessageKey());
        this.messageKey = messageKey.getMessageKey();
        this.statusCode = status.value();
        this.data = data;
    }

    public CustomResponse(Integer statusCode, MessageErrorKey messageErrorKey, List<?> data) {
        super(messageErrorKey.getMessageKey());
        this.messageKey = messageErrorKey.getMessageKey();
        this.statusCode = statusCode;
        this.data = data;
    }

    public CustomResponse(HttpStatus status, MessageErrorKey messageErrorKey, List<?> data) {
        super(messageErrorKey.getMessageKey());
        this.messageKey = messageErrorKey.getMessageKey();
        this.statusCode = status.value();
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public List<?> getData() {
        return data;
    }
}
