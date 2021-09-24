package com.example.learningspring.controller;

import com.example.learningspring.constants.BookData;
import com.example.learningspring.model.Book;
import com.example.learningspring.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

  @InjectMocks
  BookController bookController;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  private static ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testCreateBook() throws Exception {
    String uri = "/api/v1/books";
    Book book = BookData.createBookData();
    String json = mapper.writeValueAsString(book);
    when(bookService.createBook(ArgumentMatchers.any())).thenReturn(book);
    mockMvc.perform(post(uri)
           .contentType(MediaType.APPLICATION_JSON)
           .characterEncoding("utf-8")
           .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
           .andExpect(jsonPath("$.id", Matchers.equalTo(19)))
           .andExpect(jsonPath("$.title", Matchers.equalTo(book.getTitle())))
           .andExpect(jsonPath("$.authorName", Matchers.equalTo(book.getAuthorName())));
  }

  @Test
  public void testListBooks() throws Exception {
    String uri = "/api/v1/books";
    List<Book> books = BookData.listBooksData();
    when(bookService.listAll()).thenReturn(books);
    mockMvc.perform(get(uri))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", Matchers.hasSize(2)))
           .andExpect(jsonPath("$[0].title", Matchers.equalTo("ruby")));
  }
}
