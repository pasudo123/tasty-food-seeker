package org.pasudo123.tastyfoodseeker.web.exception.detail;

import lombok.Getter;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;

@Getter
public class EntityEmptyException extends RuntimeException {

    private final ErrorCode errorCode;

    public EntityEmptyException(final ErrorCode errorCode, String message, Class<?> clazz) {
        super(String.format("[%s] \n [%s] 엔티티는 존재하지 않습니다.", message, clazz.getCanonicalName()));
        this.errorCode = errorCode;
    }
}
