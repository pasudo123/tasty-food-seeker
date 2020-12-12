package org.pasudo123.tastyfoodseeker.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.web.exception.detail.JsonConvertException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(JsonConvertException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage jsonConvertException(JsonConvertException ex, WebRequest webRequest) {
        log.error("current error : \n{}", ex.getMessage());
        return ErrorMessage.builder()
                .errorCode(ex.getErrorCode())
                .build();
    }
}
