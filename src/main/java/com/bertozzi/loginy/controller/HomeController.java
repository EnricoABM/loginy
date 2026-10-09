package com.bertozzi.loginy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "contents/home :: content");
        return mv;
    }
}
