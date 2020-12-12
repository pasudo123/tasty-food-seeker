package org.pasudo123.tastyfoodseeker.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ENTITY_EMPTY_EXCEPTION(1401, "entity empty error"),
    JSON_MAPPING_EXCEPTION(1501, "json converting error");

    private long httpStatus;
    private String message;

    ErrorCode(long httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
