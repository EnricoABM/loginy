package com.bertozzi.loginy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bertozzi.loginy.dto.RegisterDTO;
import com.bertozzi.loginy.dto.UserInfoDTO;
import com.bertozzi.loginy.service.RegisterService;
import com.bertozzi.loginy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/users")
public class UserController {
    
    private final RegisterService registerService;
    private final UserService userService;

    public UserController(RegisterService registerService, UserService userService) {
        this.registerService = registerService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "contents/users :: content");
        List<UserInfoDTO> list = userService.findAll().stream().map(UserInfoDTO::from).toList();
        mv.addObject("users", list);
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView newUserForm() {
        ModelAndView mv = new ModelAndView("layout");
        mv.addObject("content", "contents/user_form :: content");
        return mv;
    }

    @PostMapping("/register")
    public String preRegister(@ModelAttribute RegisterDTO dto) {
        registerService.preRegister(dto.toModel());
        return "redirect:/users";
    }
    

    @GetMapping("/register/confirm")
    public ModelAndView confirmRegisterForm(@RequestParam String email, @RequestParam String code) {
        ModelAndView mv = new ModelAndView("contents/confirm_register");
        mv.addObject("email", email);
        mv.addObject("code", code);
        return mv;
    }

    @PostMapping("/register/confirm")
    public String confirmRegister(@RequestParam String email, @RequestParam String code, @RequestParam String password) {
        registerService.save(email, password, code);
        return "redirect:/login";
    }
    

}
