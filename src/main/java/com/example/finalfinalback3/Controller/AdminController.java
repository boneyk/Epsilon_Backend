package com.example.finalfinalback3.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public String get(){
        return "GET:: admin controller";
    }

    @PostMapping
    public String post(){
        return "POST:: admin controller";
    }

    @PutMapping
    public String Put(){
        return "PUT:: admin controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: admin controller";
    }

}
