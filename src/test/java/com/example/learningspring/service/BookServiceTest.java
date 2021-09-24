package com.example.learningspring.service;

import com.example.learningspring.constants.BookData;
import com.example.learningspring.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.learningspring.model.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  @Test
  public void whenCreateBook() {
    Book book = BookData.createBookData();
    when(bookRepository.save(ArgumentMatchers.any())).thenReturn(book);
    Book created = bookService.createBook(book);
    assertThat(created.getTitle()).isSameAs(book.getTitle());
    verify(bookRepository).save(book);
  }

  @Test
  public void shouldListBooks() throws Exception {
    when(bookService.listAll()).thenReturn(BookData.listBooksData());
  }
}
