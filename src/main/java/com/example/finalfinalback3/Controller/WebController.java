package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class WebController {

    UserService userService;
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserEntity());
        try {
            return "registration";
        }
        catch (Exception e){
            return "";
        }
    }

    @GetMapping("/signin")
    public String signin(Model model){
        model.addAttribute("user", new UserEntity());
        try {
            return "signin";
        }
        catch (Exception e){
            return "";
        }
    }

    @GetMapping("/main")
    public String main(Model model){
        try {
            model.addAttribute("user_list", Arrays.asList(userService.showAll()));
            return "main";
        }
        catch (Exception e){
            return "";
        }
    }



}
