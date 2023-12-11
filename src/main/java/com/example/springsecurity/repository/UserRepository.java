package com.example.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email); 

  User findByRole(Role role);
}
