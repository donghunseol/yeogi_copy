package com.example.final_project._core.errors.exception;

import com.example.final_project._core.utils.ApiUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 권한 없음
@Getter
public class ApiException404 extends RuntimeException {
    public ApiException404(String message) {
        super(message);
    }

    public ApiUtil.ApiResult<?> body() {
        return ApiUtil.error(getMessage(), HttpStatus.NOT_FOUND);
    }

    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}