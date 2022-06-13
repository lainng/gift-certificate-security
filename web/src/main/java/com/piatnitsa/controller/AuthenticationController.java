package com.piatnitsa.controller;

import com.piatnitsa.dto.UserCredentialsDto;
import com.piatnitsa.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public UserCredentialsDto authorizeUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentialsDto.getEmail(), userCredentialsDto.getPassword()));
        String token = jwtProvider.generateToken(userCredentialsDto.getEmail());
        userCredentialsDto.setToken(token);
        return userCredentialsDto;
    }
}
