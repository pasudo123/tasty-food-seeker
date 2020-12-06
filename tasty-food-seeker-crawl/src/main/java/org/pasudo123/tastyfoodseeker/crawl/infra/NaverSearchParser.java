package org.pasudo123.tastyfoodseeker.crawl.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pasudo123.tastyfoodseeker.crawl.infra.pojo.NaverLocationItems;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverSearchParser {

    private final ObjectMapper mapper;

    public Optional<NaverLocationItems> getLocationItemsByResponse(final String response) {
        try {
            return Optional.of(mapper.readValue(response, NaverLocationItems.class));
        } catch (JsonProcessingException e) {
            log.error("parsing error : {}", e.getMessage());
            return Optional.empty();
        }
    }
}
