package com.example.learningspring.service;


import com.example.learningspring.model.Book;
import com.example.learningspring.model.User;
import com.example.learningspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;

import com.example.learningspring.exception.EmailAlreadyExists;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository service;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User signUp(User user) {
    System.out.println("----------------");
    System.out.println(user);
    System.out.println(user.getPassword().toString());
    System.out.println("----------------");
    if(isEmailPresent(user.getEmail())) {
      User new_user = new User();
      new_user.setEmail(user.getEmail());
      new_user.setUserName(user.getUserName());
      new_user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      new_user.setRoles("USER");
      return service.save(new_user);
    } else {
      throw new EmailAlreadyExists("email already exists " + user.getEmail());
    }
  }

  public boolean isEmailPresent(String email) {
    User user = service.findByEmail(email);
    if(null==user) {
      return true;
    } else {
      return false;
    }
  }
}
