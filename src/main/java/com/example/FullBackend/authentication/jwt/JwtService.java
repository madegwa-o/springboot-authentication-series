package com.example.FullBackend.authentication.jwt;

import com.example.FullBackend.authentication.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;


    public String extractUserName(String jwtToken) {
        return null;
    }

    public boolean isTokenValid(String jwtToken,String username) {
        return true;
    }

    public String getAccessToken(UserDetails userDetails) {
        String username = userDetails.getUsername();

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("roles", userDetails.getAuthorities());
        claims.put("iat", new Date().getTime());
        claims.put("exp", new Date().getTime() + accessTokenExpiration);


        return createToken(username, claims, accessTokenExpiration);
    }

    public String getRefreshToken(UserDetails userDetails) {
        String username = userDetails.getUsername();

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("iat", new Date().getTime());
        claims.put("exp", new Date().getTime() + refreshTokenExpiration);

        return createToken(username, claims, refreshTokenExpiration);
    }

    private String createToken(String username, Map<String,Object> claims,long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
