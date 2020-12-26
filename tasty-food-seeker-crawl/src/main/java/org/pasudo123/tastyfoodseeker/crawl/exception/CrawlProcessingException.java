package org.pasudo123.tastyfoodseeker.crawl.exception;

import org.pasudo123.tastyfoodseeker.crawl.exception.code.ErrorCode;

public class CrawlProcessingException extends RuntimeException {

    public CrawlProcessingException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
