package com.example.FullBackend.authentication;


import com.example.FullBackend.authentication.dto.LoginRequest;
import com.example.FullBackend.authentication.dto.LoginResponse;
import com.example.FullBackend.authentication.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse;
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            String accessToken = jwtService.getAccessToken(userDetails);
            String refreshToken = jwtService.getRefreshToken(userDetails);

            Cookie cookie = new Cookie("REFRESH_TOKEN", refreshToken);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(cookie);


            loginResponse =  LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .message("success")
                    .build();

           return  ResponseEntity.ok(loginResponse);


        }catch (AuthenticationException e) {
            log.error("Login failed for user {}", loginRequest.getUsername(), e);


            LoginResponse errorResponse = LoginResponse.builder()
                    .message("Invalid username or password")
                    .build();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

    }

    @GetMapping("/mock-data")
    public String mockData() {
        return "mock-data";
    }


}
