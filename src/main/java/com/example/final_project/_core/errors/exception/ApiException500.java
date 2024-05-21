package com.example.final_project._core.errors.exception;

import com.example.final_project._core.utils.ApiUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 서버 에러
@Getter
public class ApiException500 extends RuntimeException {
    public ApiException500(String message) {
        super(message);
    }

    public ApiUtil.ApiResult<?> body() {
        return ApiUtil.error("server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus status() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}