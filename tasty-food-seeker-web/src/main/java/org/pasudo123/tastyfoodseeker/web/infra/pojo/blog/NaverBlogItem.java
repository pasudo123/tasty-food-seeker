package org.pasudo123.tastyfoodseeker.web.infra.pojo.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class NaverBlogItem {

    private static final String BOLD_PATTERN = "\\<(\\/)?b\\>";

    @Setter
    private String link;

    private String title;
    private String description;
    private String postdate;

    public void setPostdate(final String postdate) {
        final String year = postdate.substring(0, 4);
        final String month = postdate.substring(4, 6);
        final String day = postdate.substring(6);
        this.postdate = year.concat("-").concat(month).concat("-").concat(day);
    }

    public void setTitle(final String title) {
        this.title = title.replaceAll(BOLD_PATTERN, Strings.EMPTY);
    }

    public void setDescription(final String description) {
        this.description = description.replaceAll(BOLD_PATTERN, Strings.EMPTY);
    }
}
