package com.example.learningspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.learningspring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public User findByEmail(String email);
}
