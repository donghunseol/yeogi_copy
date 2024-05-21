package com.example.final_project._core.errors;

import com.example.final_project._core.errors.exception.*;
import com.example.final_project._core.utils.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// RuntimeException이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice // 데이터 응답
public class MyApiExceptionHandler {

    @ExceptionHandler(ApiException400.class)
    public ResponseEntity<?> badRequest(ApiException400 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(ApiException401.class)
    public ResponseEntity<?> unAuthorized(ApiException401 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(ApiException403.class)
    public ResponseEntity<?> forbidden(ApiException403 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(ApiException404.class)
    public ResponseEntity<?> notFound(ApiException404 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(ApiException500.class)
    public ResponseEntity<?> serverError(ApiException500 e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e) {
        log.error(e.getMessage());
        ApiUtil.ApiResult<?> apiResult = ApiUtil.error("unknown server error", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}