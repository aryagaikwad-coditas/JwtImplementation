package com.example.JwtImplementation.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Username cannot be left  blank")
    private String username;

    @NotBlank(message = "Password cannot be left blank")
    private String password;
}
