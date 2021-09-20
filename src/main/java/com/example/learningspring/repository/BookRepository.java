package com.example.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningspring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

  public Book findByTitle(String title);
}
