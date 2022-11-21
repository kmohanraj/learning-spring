package com.example.learningspring.controller;


import com.example.learningspring.model.Book;
import com.example.learningspring.model.User;
import com.example.learningspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "register")
  public Map<String, String > register(@Valid @RequestBody User user ) {
    User _user = userService.signUp(user);
    Map<String, String> response =  new HashMap<>();
    response.put("success", "User registered successfully.");
    return response;
  }

}
