package com.ics.catpfanb.apirest.security;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Set;

@Value
@RequiredArgsConstructor
@Builder

public class UserDTO {
    String username;
    String email;
    String firstName;
    String lastName;
    String password;
    Set<String> roles;

}
