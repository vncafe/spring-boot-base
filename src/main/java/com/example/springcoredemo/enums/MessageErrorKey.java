package com.example.springcoredemo.enums;

public enum MessageErrorKey {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    NOT_FOUND("NOT_FOUND"),
    BAD_REQUEST("BAD_REQUEST"),
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED"),
    CONFLICT("CONFLICT"),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE"),
    UNPROCESSABLE_ENTITY("UNPROCESSABLE_ENTITY"),
    TOO_MANY_REQUESTS("TOO_MANY_REQUESTS");

    private final String messageKey;

    MessageErrorKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
