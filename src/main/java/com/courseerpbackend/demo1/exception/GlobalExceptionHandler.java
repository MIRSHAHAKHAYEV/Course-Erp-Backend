package com.courseerpbackend.demo1.exception;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import com.courseerpbackend.demo1.entity.enums.response.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException e){
        return ResponseEntity.status(e.getResponseMessages().status()).body(BaseResponse.error(e));
    }
 }
