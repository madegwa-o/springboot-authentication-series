package com.karu.counsellingsection.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        // Spring Security handles the authentication process
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserInfo(HttpSession session) {
        // Return user info based on session
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(user);
    }


}
