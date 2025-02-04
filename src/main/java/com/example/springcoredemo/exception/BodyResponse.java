package com.example.springcoredemo.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.example.springcoredemo.enums.MessageErrorKey;
import com.example.springcoredemo.enums.MessageKey;

public class BodyResponse {
    private Integer statusCode;
    private String message;
    private List<?> data;

    private final MessageSource messageSource; // MessageSource để lấy thông điệp từ tài nguyên

    // Phương thức lấy thông điệp từ messageSource theo Locale hiện tại
    private String translate(String messageKey) {
        Locale currentLocale = LocaleContextHolder.getLocale(); // Lấy Locale hiện tại từ LocaleContextHolder
        return messageSource.getMessage(messageKey, null, currentLocale); // Lấy thông điệp từ MessageSource
    }

    public BodyResponse(MessageErrorKey messageKey, MessageSource messageSource) { // trường hợp lỗi
        this.messageSource = messageSource;
        this.statusCode = getStatusCodeError(messageKey);
        this.message = translate(messageKey.getMessageKey());
    }

    public BodyResponse(Integer statusCode, MessageErrorKey messageKey, MessageSource messageSource) { // trường hợp lỗi
        this.messageSource = messageSource;
        this.statusCode = statusCode;
        this.message = translate(messageKey.getMessageKey());
    }

    public BodyResponse(Integer statusCode, MessageKey messageKey, MessageSource messageSource) { // tùy chỉnhstatusCode
        this.messageSource = messageSource;
        this.statusCode = statusCode;
        this.message = translate(messageKey.getMessageKey());
    }

    public BodyResponse(HttpStatus status, MessageKey messageKey, MessageSource messageSource) { // tùy chỉnh HttpStatus
        this.messageSource = messageSource;
        this.statusCode = status.value();
        this.message = translate(messageKey.getMessageKey());
    }

    public BodyResponse(HttpStatusCode statusCode, String message, MessageSource messageSource) { // không có bản dịch
        this.messageSource = messageSource;
        this.statusCode = statusCode.value();
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    private Integer getStatusCodeError(MessageErrorKey messageKey) {
        try {
            // Chuyển đổi messageKey thành tên của một giá trị trong HttpStatus
            String statusName = messageKey.name(); // Lấy tên của enum (VD: INTERNAL_SERVER_ERROR)
            HttpStatus status = HttpStatus.valueOf(statusName); // Tìm kiếm trong HttpStatus

            return status.value(); // Trả về mã trạng thái
        } catch (IllegalArgumentException e) {
            // Nếu không tìm thấy, trả về INTERNAL_SERVER_ERROR mặc định
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }
}
