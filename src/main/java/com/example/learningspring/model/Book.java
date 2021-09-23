package com.example.learningspring.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
//@Table(name = "books")

public class Book {

  private long id;
  @NotEmpty(message = "Title can not be empty")
  private String title;
  @NotEmpty(message = "Author Name can not be empty")
  private String authorName;
  private String description;
  private String published;

  public Book() {

  }

  public Book(String title, String authorName, String description, String published) {
    this.title = title;
    this.authorName = authorName;
    this.description = description;
    this.published = published;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "title", nullable = false, unique = true)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "author_name", nullable = false)
  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  @Column(name = "description", nullable = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "published")
  public String getPublished() {
    return published;
  }

  public void setPublished(String published) {
    this.published = published;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", authorName=" + authorName + ", description=" + description + ", published=" + published + "]";
  }
}
