package com.example.learningspring.controller;

import com.example.learningspring.model.Book;
import com.example.learningspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping("/books")
  public List<Book> getAllBooks(){
    return bookRepository.findAll();
  }

  @PostMapping("/books")
  public Book createBook(@RequestBody Book book) {
    return bookRepository.save(new Book(book.getTitle(), book.getAuthorName(), book.getDescription(), false));
  }
}
