package org.pasudo123.tastyfoodseeker.crawl.exception;

import org.pasudo123.tastyfoodseeker.crawl.exception.code.ErrorCode;

public class InValidParamsException extends CrawlProcessingException{
    public InValidParamsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
