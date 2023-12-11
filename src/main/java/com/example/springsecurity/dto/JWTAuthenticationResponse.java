package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponse{
  private String token;

  private String refreshToken;
}