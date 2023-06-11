package com.example.api.security.controller;

import com.example.api.security.dto.AuthRequest;
import com.example.api.security.dto.TokenResponse;
import com.example.api.security.model.User;
import com.example.api.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    UserService userService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<TokenResponse> signup(@RequestBody AuthRequest authRequest) {
        User user = userService.signup(authRequest.getUsername(), authRequest.getPassword());
        String token = userService.signin(authRequest.getUsername(), authRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse(user.getUsername(), token);
        return new ResponseEntity<TokenResponse>(tokenResponse, HttpStatus.CREATED);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest) {
        String token = userService.signin(authRequest.getUsername(), authRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse(authRequest.getUsername(), token);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
