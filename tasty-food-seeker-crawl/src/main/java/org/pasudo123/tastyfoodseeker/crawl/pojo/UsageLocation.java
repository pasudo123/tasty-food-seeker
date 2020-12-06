package org.pasudo123.tastyfoodseeker.crawl.pojo;

import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsageLocation {
    private static final Pattern PATTERN_BRACKET = Pattern.compile("\\((.*?)\\)");
    private final LocalDate usageDate;
    private final String location;


    public UsageLocation(final LocalDate usageDate, final String location) {
        this.usageDate = usageDate;
        this.location = location.trim();
    }

    public boolean isExistLocation() {
        return (!this.location.isEmpty() && !"-".equals(this.location));
    }

    public UsageLocationInfo toRestaurantInfo() {
        final Matcher matcher = PATTERN_BRACKET.matcher(location);

        if(matcher.find()) {
            final String name = location.substring(0, matcher.start());
            final String address = location.substring(matcher.start())
                    .replaceAll("\\(", Strings.EMPTY)
                    .replaceAll("\\)", Strings.EMPTY);

            return new UsageLocationInfo(name, address);
        }

        // 괄호가 없는 경우도 있을 수 있기 때문.
        return new UsageLocationInfo(location, Strings.EMPTY);
    }
}
