package org.pasudo123.tastyfoodseeker.web.exception.detail;

import lombok.Getter;
import org.pasudo123.tastyfoodseeker.web.exception.ErrorCode;

@Getter
public class JsonConvertException extends RuntimeException {

    private final ErrorCode errorCode;

    public JsonConvertException(final ErrorCode errorCode, String message, String origin, Class<?> clazz) {
        super(String.format("[%s] \n 스트링 [%s] 를 클래스 [%s] 으로 변환시에 에러가 발생하였습니다.", message, origin, clazz.getCanonicalName()));
        this.errorCode = errorCode;
    }
}
