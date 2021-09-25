package com.example.learningspring.controller;

import com.example.learningspring.constants.BookData;
import com.example.learningspring.exception.BookAlreadyExists;
import com.example.learningspring.exception.BookNotFoundException;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
  public void createBook_whenPostMethod() throws Exception {
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
  public void listAllBooks_whenGetMethod() throws Exception {
    String uri = "/api/v1/books";
    List<Book> books = BookData.listBooksData();
    when(bookService.listAll()).thenReturn(books);
    mockMvc.perform(get(uri))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", Matchers.hasSize(2)))
           .andExpect(jsonPath("$[0].title", Matchers.equalTo("ruby")));
  }

  @Test
  public void listBookById_whenGetMethod() throws Exception {
    String uri = "/api/v1/book/";
    Book book = BookData.createBookData();
    String json = mapper.writeValueAsString(book);
    when(bookService.get(book.getId())).thenReturn(book);
    mockMvc.perform(get(uri + "19")
           .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("title", Matchers.equalTo("ruby")));
  }

  @Test
  public void updateBook_whenPutMethod() throws Exception {
    String uri = "/api/v1/book/";
    Book book = BookData.createBookData();
    book.setTitle("Spring Boot");
    String json = mapper.writeValueAsString(book);
    when(bookService.update(BookData.createBookData(), book)).thenReturn(book);
    mockMvc.perform(put(uri + "19")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  
  @Test
  public void deleteBookById_whenDeleteMethod() throws Exception {
    String uri = "/api/v1/book/";
    Book book = BookData.createBookData();
    doNothing().when(bookService).delete(book.getId());
    mockMvc.perform(delete(uri+"19").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
  }

  @Test
  public void shouldThrowException_whenBookNotFound() throws Exception {
    String uri = "/api/v1/book/";
    Book book = BookData.createBookData();
    Mockito.doThrow(new BookNotFoundException("19")).when(bookService).get(book.getId());
    mockMvc.perform(get(uri + "19")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  public void shouldThrowException_whenBookTitleIsEmpty() throws Exception {
    String uri = "/api/v1/books";
    Book book = BookData.createBookData();
    book.setTitle("");
    String json = mapper.writeValueAsString(book);
    Mockito.doThrow(new BookAlreadyExists("ruby")).when(bookService).createBook(book);
    mockMvc.perform(post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }
}
