package com.example.JwtImplementation.Service;

import com.example.JwtImplementation.Dto.AuthResponse;
import com.example.JwtImplementation.Dto.LoginRequest;
import com.example.JwtImplementation.Dto.RegisterRequest;
import com.example.JwtImplementation.Exception.ApiException;
import com.example.JwtImplementation.Repository.UserRepository;
import com.example.JwtImplementation.Util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.builder;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse login(@Valid LoginRequest request) {
        com.example.JwtImplementation.Entity.User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found in the database"));
        String token = jwtUtil.generateToken(user.getUsername(), com.example.JwtImplementation.Entity.User.Role.ADMIN);

        log.info("Authentication successful");
        return new AuthResponse(token);
    }

    public void register(@Valid RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            log.error("Registration has failed , The User already exists");
            throw new ApiException(HttpStatus.CONFLICT, "The User already exists ");
        }

        User user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .enabled(true)
                .build();


        userRepository.save(user);
        log.info("User {} has been registered successfully", user.getUsername());

    }
}
