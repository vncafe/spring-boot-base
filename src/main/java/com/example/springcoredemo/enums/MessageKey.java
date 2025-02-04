package com.example.springcoredemo.enums;

public enum MessageKey {
    hello("hello");

    private final String messageKey;

    MessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
