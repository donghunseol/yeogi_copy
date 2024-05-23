package com.example.final_project._core.errors;

import com.example.final_project._core.errors.exception.*;
import com.example.final_project._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// RuntimeException이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice // 데이터 응답
public class MyApiExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : " + e.getMessage());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e, HttpServletRequest request) {
        log.warn("401 : " + e.getMessage()); // ex) 로그인 실패 다이렉트 메세지 [위험도는 낮지만 주의해야 하는 점이 있다 : 강제로 접속하는 인원이 발생]
        log.warn("IP : " + request.getRemoteAddr()); // 누군지 IP 확인
        log.warn("Agent : " + request.getHeader("User-Agent")); // 장비 확인
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : " + e.getMessage());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.info("404 : " + e.getMessage());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        ApiUtil.ApiResult<?> apiResult = ApiUtil.error("unknown server error", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}