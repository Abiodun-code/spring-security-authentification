package com.example.springsecurity.service.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springsecurity.dto.JWTAuthenticationResponse;
import com.example.springsecurity.dto.RefreshTokenRequest;
import com.example.springsecurity.dto.SignInRequest;
import com.example.springsecurity.dto.SignUpRequest;
import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.AuthenticationService;
import com.example.springsecurity.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  public User signUp(SignUpRequest signupRequest){
    User user = new User();
    user.setEmail(signupRequest.getEmail());
    user.setFirstname(signupRequest.getFirstname());
    user.setLastname(signupRequest.getLastname());
    user.setRole(Role.USER);
    user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    return userRepository.save(user);
  }

  public JWTAuthenticationResponse signIn(SignInRequest signInRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

    var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
    var jwt = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

    JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

    jwtAuthenticationResponse.setToken(jwt);
    jwtAuthenticationResponse.setRefreshToken(refreshToken);
    return jwtAuthenticationResponse;
  }

  public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
    String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
      var jwt = jwtService.generateToken(user);

      JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

      jwtAuthenticationResponse.setToken(jwt);
      jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
      return jwtAuthenticationResponse;
    }
    return null;
  }
}
