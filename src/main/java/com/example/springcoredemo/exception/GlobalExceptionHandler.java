package com.example.springcoredemo.exception;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;

import com.example.springcoredemo.enums.MessageErrorKey;
import com.example.springcoredemo.enums.MessageKey;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice // Xử lý các ngoại lệ của các Controller
public class GlobalExceptionHandler {
    private final MessageSource messageSource;
    private final Logger logger;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    }

    @ExceptionHandler(CustomResponse.class)
    public ResponseEntity<BodyResponse> handleCustomResponse(CustomResponse e) {
        Integer statusCode = e.getStatusCode();
        HttpStatus status = HttpStatus.valueOf(statusCode);
        BodyResponse bodyResponse = null;
        if (status.is2xxSuccessful() || status.is3xxRedirection() || status.is1xxInformational()) {
            MessageKey messageKey = null;
            for (MessageKey key : MessageKey.values()) {
                if (key.getMessageKey().equals(e.getMessageKey())) {
                    messageKey = key;
                    break;
                }
            }
            if (messageKey != null) { // có bản dịch
                bodyResponse = new BodyResponse(status, messageKey, messageSource);
            } else { // không có bản dịch
                bodyResponse = new BodyResponse(status, e.getMessageKey(), messageSource);
            }

        } else {
            MessageErrorKey messageErrorKey = null;
            for (MessageErrorKey key : MessageErrorKey.values()) {
                if (key.getMessageKey().equals(e.getMessageKey())) {
                    messageErrorKey = key;
                    break;
                }
            }
            if (messageErrorKey == null) { // không rõ
                messageErrorKey = MessageErrorKey.INTERNAL_SERVER_ERROR;
            }
            bodyResponse = new BodyResponse(statusCode, messageErrorKey, messageSource);
        }

        List<?> data = e.getData();
        if (data != null) {
            bodyResponse.setData(data);
        }

        return new ResponseEntity<BodyResponse>(bodyResponse, HttpStatusCode.valueOf(bodyResponse.getStatusCode()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BodyResponse> handleCustomException(CustomException e) {
        BodyResponse bodyResponse = e.getBodyResponse();
        return new ResponseEntity<BodyResponse>(bodyResponse, HttpStatusCode.valueOf(bodyResponse.getStatusCode()));
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<BodyResponse> handleHttpStatusCodeException(HttpStatusCodeException e) {
        // Lấy HttpStatus từ HttpStatusCodeException
        HttpStatusCode statusCode = e.getStatusCode();
        // Trả về BodyResponse với mã trạng thái và thông điệp
        BodyResponse bodyResponse = new BodyResponse(statusCode, e.getMessage(), messageSource);
        return new ResponseEntity<BodyResponse>(bodyResponse, HttpStatusCode.valueOf(bodyResponse.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BodyResponse> handleException(Exception e) {
        logger.severe(e.getMessage());
        MessageErrorKey messageErrorKey = null;
        BodyResponse bodyResponse = null;

        for (MessageErrorKey key : MessageErrorKey.values()) {
            if (key.getMessageKey().equals(e.getMessage())) {
                messageErrorKey = key;
                break;
            }
        }
        if (messageErrorKey == null) { // không rõ
            messageErrorKey = MessageErrorKey.INTERNAL_SERVER_ERROR;
        }
        if (messageErrorKey != null) {
            bodyResponse = new BodyResponse(messageErrorKey, messageSource);
        }

        return new ResponseEntity<BodyResponse>(bodyResponse, HttpStatusCode.valueOf(bodyResponse.getStatusCode()));
    }
}
