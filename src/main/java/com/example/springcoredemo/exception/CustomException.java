package com.example.springcoredemo.exception;

public class CustomException extends RuntimeException {
    private final BodyResponse bodyResponse;

    public CustomException(BodyResponse bodyResponse) {
        super(bodyResponse.getMessage()); // Sử dụng message trong BodyResponse làm thông điệp của ngoại lệ
        this.bodyResponse = bodyResponse;
    }

    public BodyResponse getBodyResponse() {
        return bodyResponse;
    }
}