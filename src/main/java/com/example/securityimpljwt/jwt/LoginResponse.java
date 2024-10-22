package com.example.securityimpljwt.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LoginResponse {

    private String jwt;
    private String username;
    private List<String> roles;

}
