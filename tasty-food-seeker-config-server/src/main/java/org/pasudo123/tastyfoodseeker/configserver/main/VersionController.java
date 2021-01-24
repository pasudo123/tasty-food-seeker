package org.pasudo123.tastyfoodseeker.configserver.main;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VersionController {

    private final Environment environment;
    private static final Map<String, Object> VERSION_STORE = new HashMap<>();

    @PostConstruct
    public void init() {
        VERSION_STORE.put("version", environment.getProperty("version", "not found version.properties"));
    }

    @GetMapping("version")
    public Map<String, Object> currentVersion() {
        return VERSION_STORE;
    }
}
