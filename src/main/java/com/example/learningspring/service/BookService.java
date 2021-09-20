package com.example.learningspring.service;

import com.example.learningspring.exception.BookAlreadyExists;
import com.example.learningspring.exception.BookNotFoundException;
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
    if(isTitlePresent(book.getTitle())) {
      return service.save(book);
    } else {
      throw new BookAlreadyExists("Title already exist with this title: " + book.getTitle());
    }

  }

  public Book update(Book bookData, Book book) {
    bookData.setTitle(book.getTitle());
    bookData.setAuthorName(book.getAuthorName());
    bookData.setDescription(book.getDescription());
    bookData.setPublished(book.getPublished());
    return service.save(bookData);
  }

  public Book get(Long id) {
    try {
      return service.findById(id).get();
    } catch (Exception ex) {
      throw new BookNotFoundException("Book id not found : " + id);
    }
  }

  public void delete(Long id) {
    try {
      service.deleteById(id);
    } catch (BookNotFoundException ex) {
      throw new BookNotFoundException("Book id not found : " + id);
    }

  }

  public boolean isTitlePresent(String title) {
    Book book = service.findByTitle(title);
    if(null==book) {
      return true;
    } else {
      return false;
    }
  }
}
