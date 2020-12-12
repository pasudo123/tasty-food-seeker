package org.pasudo123.tastyfoodseeker.web.exception.detail;

import lombok.Getter;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;

@Getter
public class JsonConvertException extends RuntimeException {

    private final ErrorCode errorCode;

    public JsonConvertException(final ErrorCode errorCode, String message, String origin, Class<?> clazz) {
        super(String.format("[%s] \n [%s] convert to class [%s]", message, origin, clazz.getCanonicalName()));
        this.errorCode = errorCode;
    }
}
