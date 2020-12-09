package org.pasudo123.tastyfoodseeker.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping
    public String main(Model model) {
        model.addAttribute("customMessage", "sadffadsafa");
        return "index";
    }

//    @ModelAttribute("baseLocation")
//    public void baseLocation(Model model) {
//        // spring mvc 는 request handler method 호출 이전에 해당 메소드를 우선적으로 호출 수행
//
//    }
}
