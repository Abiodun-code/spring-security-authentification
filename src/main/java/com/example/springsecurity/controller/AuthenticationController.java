package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.dto.JWTAuthenticationResponse;
import com.example.springsecurity.dto.RefreshTokenRequest;
import com.example.springsecurity.dto.SignInRequest;
import com.example.springsecurity.dto.SignUpRequest;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody SignUpRequest signupRequest){
    return ResponseEntity.ok(authenticationService.signUp(signupRequest));
  }

  @PostMapping("/signin")
  public ResponseEntity<JWTAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
    return ResponseEntity.ok(authenticationService.signIn(signInRequest));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
    return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
  }
}
