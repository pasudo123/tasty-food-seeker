package org.pasudo123.tastyfoodseeker.crawl.exception;

import org.pasudo123.tastyfoodseeker.crawl.exception.code.ErrorCode;

public class WebDriverInitializeException extends CrawlProcessingException {
    public WebDriverInitializeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
