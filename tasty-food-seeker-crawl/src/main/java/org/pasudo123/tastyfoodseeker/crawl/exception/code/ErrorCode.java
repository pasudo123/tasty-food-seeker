package org.pasudo123.tastyfoodseeker.crawl.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    WEB_DRIVER_INIT_EXCEPTION(1501, "WebDriver Initialize Exception"),
    INVALID_PARAMS_EXCEPTION(1502, "Invalid Params Exception");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
