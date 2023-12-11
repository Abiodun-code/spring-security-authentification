package com.example.springsecurity.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserDetailsService userDetailsService(){
    return new UserDetailsService(){

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
      }
      
    };
  }
}
