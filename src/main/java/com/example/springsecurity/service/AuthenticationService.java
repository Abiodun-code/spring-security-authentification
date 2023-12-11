package com.example.springsecurity.service;

import com.example.springsecurity.dto.JWTAuthenticationResponse;
import com.example.springsecurity.dto.RefreshTokenRequest;
import com.example.springsecurity.dto.SignInRequest;
import com.example.springsecurity.dto.SignUpRequest;
import com.example.springsecurity.entity.User;

public interface AuthenticationService {
  
  User signUp(SignUpRequest signupRequest);

  JWTAuthenticationResponse signIn(SignInRequest signInRequest);

  JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
