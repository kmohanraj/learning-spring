package com.example.learningspring.controller;

import com.example.learningspring.model.Book;
import com.example.learningspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookController {

  @Autowired
  private BookService service;

  @GetMapping(value = "books")
  public List<Book> getAllBooks(){
    return service.listAll();
  }

  @PostMapping(value = "books")
  public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
    Book _book = service.save(new Book(book.getTitle(), book.getAuthorName(), book.getDescription(), false));
    return new ResponseEntity<>(_book, HttpStatus.CREATED);
  }

  @GetMapping(value = "book/{id}")
  public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
    Book bookData = service.get(id);
    return new ResponseEntity<>(bookData, HttpStatus.OK);
  }

  @PutMapping(value = "book/{id}")
  public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
    Book bookData = service.get(id);
    Book temp = service.update(bookData, book);
    return new ResponseEntity<>(temp, HttpStatus.OK);
  }

  @DeleteMapping(value = "book/{id}")
  public Map<String, Boolean> deleteBook(@PathVariable("id") Long id) {
    service.delete(id);
    Map<String, Boolean> response =  new HashMap<>();
    response.put("Book deleted successfully.", Boolean.TRUE);
    return response;
  }
}
