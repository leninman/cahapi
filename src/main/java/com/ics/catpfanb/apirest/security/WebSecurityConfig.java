package com.ics.catpfanb.apirest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
            return http
                    .csrf(csrf->csrf.disable())
                    .authorizeHttpRequests(authorize->
                            authorize.anyRequest().authenticated())
                    .build();
    }

}
