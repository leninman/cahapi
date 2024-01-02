package com.ics.catpfanb.apirest.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class Authcontroller {

    @PostMapping("login")
    public String login(){
        return "login from public endpoint";
    }
    @PostMapping("register")
    public String register(){
        return "registered from public endpoint";
    }


}
