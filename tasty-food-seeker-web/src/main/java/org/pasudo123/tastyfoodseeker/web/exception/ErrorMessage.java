package org.pasudo123.tastyfoodseeker.web.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorMessage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private long httpStatus;
    private String message;
    private String errorTime;

    @Builder
    public ErrorMessage(final ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
        this.errorTime = LocalDateTime.now().format(FORMATTER);
    }
}
