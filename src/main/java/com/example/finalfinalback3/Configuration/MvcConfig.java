package com.example.finalfinalback3.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        //registry.addViewController("/").setViewName("guestGreeting");
        //registry.addViewController("/main").setViewName("main");
        //registry.addViewController("/signin").setViewName("singin");
    }


}
