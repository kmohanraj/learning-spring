package com.example.learningspring;

import com.example.learningspring.controller.BookController;
import com.example.learningspring.controller.BookControllerAdvice;
import com.example.learningspring.model.Book;
import com.example.learningspring.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class BookControllerTest extends AbstractTest {

  @InjectMocks
  private BookController bookController;

  @MockBean
  private BookService bookService;

  @Mock
  private HttpServletRequest request;

  @Autowired
  private MockMvc mockMvc;

  private static ObjectMapper mapper = new ObjectMapper();

  @Test
  public void createBooks() throws Exception {
    String uri = "/books";
    Book book = new Book();
    book.setId(5);
    book.setTitle("ruby");
    book.setAuthorName("yuhi");
    book.setDescription("ruby programming language");
    book.setPublished("in-active");
    Mockito.when(bookService.save(ArgumentMatchers.any())).thenReturn(book);
    String inputJson = mapper.writeValueAsString(book);
    mockMvc.perform(post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
            .content(inputJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", Matchers.equalTo(5)))
            .andExpect(jsonPath("$.title", Matchers.equalTo("ruby")));
  }

}
