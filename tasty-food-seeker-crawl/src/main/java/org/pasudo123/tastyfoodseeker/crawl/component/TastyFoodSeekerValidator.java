package org.pasudo123.tastyfoodseeker.crawl.component;

import org.openqa.selenium.WebDriver;
import org.pasudo123.tastyfoodseeker.crawl.exception.InValidParamsException;
import org.pasudo123.tastyfoodseeker.crawl.exception.code.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class TastyFoodSeekerValidator {

    public void isValidDriver(final WebDriver driver) {
        if(driver == null) {
            throw new InValidParamsException(ErrorCode.INVALID_PARAMS_EXCEPTION);
        }
    }

    public void isValidUrl(final String url) {
        if(url.isEmpty()) {
            throw new InValidParamsException(ErrorCode.INVALID_PARAMS_EXCEPTION);
        }
    }

    public void isValidYearAndMonth(final int year, final int month) {
        if(year < 1 || month < 1) {
            throw new InValidParamsException(ErrorCode.INVALID_PARAMS_EXCEPTION);
        }
    }
}
