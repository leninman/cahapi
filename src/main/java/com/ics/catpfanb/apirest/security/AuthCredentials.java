package com.ics.catpfanb.apirest.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String userName;
    private String password;
}
