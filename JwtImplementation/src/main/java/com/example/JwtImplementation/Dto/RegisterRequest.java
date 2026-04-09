package com.example.JwtImplementation.Dto;

import com.example.JwtImplementation.Entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username cannot be  blank")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6 , message = "Password must be strong")
    private String password;

    @NotBlank(message = "Email cannot be blank ")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "User role cannot be null ")
    private User.Role role;
}
