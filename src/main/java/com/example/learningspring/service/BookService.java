package com.example.learningspring.service;


import com.example.learningspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.learningspring.model.Book;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookService {

  @Autowired
  private BookRepository service;

  public List<Book> listAll() {
    return service.findAll();
  }

  public Book save(Book book) {
    return service.save(book);
  }

  public Book update(Book bookData, Book book) {
    bookData.setTitle(book.getTitle());
    bookData.setAuthorName(book.getAuthorName());
    bookData.setDescription(book.getDescription());
    bookData.setPublished(book.getPublished());
    return service.save(bookData);
  }

  public Book get(Long id) {
    return service.findById(id).get();
  }

  public void delete(Long id) {
    service.deleteById(id);
  }
}
