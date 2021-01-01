package org.pasudo123.tastyfoodseeker.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value = {"/",
            "/explore", "/explore/*"})
    public String main() {
        return "index";
    }
}
