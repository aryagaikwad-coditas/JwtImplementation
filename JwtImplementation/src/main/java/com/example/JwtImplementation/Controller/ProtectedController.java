package com.example.JwtImplementation.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProtectedController {


    @GetMapping("/api/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Map<String, String> userProfile(Authentication auth) {
        log.info("Profile accessed by: {}", auth.getName());
        return Map.of(
                "message", "Welcome to your profile",
                "user", auth.getName(),
                "role", auth.getAuthorities().iterator().next().getAuthority()
        );
    }

    @GetMapping("/api/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, String> adminDashboard(Authentication auth) {
        log.info("Admin dashboard accessed by: {}", auth.getName());
        return Map.of(
                "message", "Welcome to the admin dashboard",
                "admin", auth.getName()
        );
    }
}