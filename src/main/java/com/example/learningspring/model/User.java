package com.example.learningspring.model;


import javax.persistence.*;

@Entity

public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(name = "user_name")
  private String userName;
  private String email;
  private String password;
//  private boolean active;
  private String roles;

  public User() {

  }
  public User(String userName, String email, String passowrd, String roles) {
    this.userName = userName;
    this.email = email;
    this.password = passowrd;
//    this.active = active;
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public boolean isActive() {
//    return active;
//  }
//
//  public void setActive(boolean active) {
//    this.active = active;
//  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
  }
}
