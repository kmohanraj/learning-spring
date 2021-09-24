package com.example.learningspring.constants;

import com.example.learningspring.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookData {

  public static Book createBookData() {
    Book book = new Book();
    book.setId(19);
    book.setTitle("ruby");
    book.setAuthorName("yuhi");
    book.setDescription("ruby programming language");
    book.setPublished("in-active");
    return book;
  }

  public static List<Book> listBooksData() {
    List<Book> books = new ArrayList<>();
    Book book1 = createBookData();
    Book book2 = createBookData();
    book1.setId(19);
    book2.setId(20);
    book2.setTitle("Spring Booot");
    books.add(book1);
    books.add(book2);
    return books;
  }
}
