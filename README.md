### Learning Spring Boot
Contents:
- [x] CRUD Operation
- [ ] Exception handling & Validation
- [ ] Unit Test

#### 1. CRUD RESTFul APIs
Create the REST APIs for creating, retrieving, updating and deleting a Book

<details>
<summary>Click to view CRUD</summary>

##### Dependencies

> Spring Web \
> Spring Data JPA \
> MySQL Driver \
> Spring Boot DevTools

##### Configuring MySQL Database

src/main/java/resources/application.properties
```

## Spring DATASOURCE (  DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:3306/book_management?useSSL=false
spring.datasource.username = hive
spring.datasource.password = letmein

## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL57InnoDBDialect


# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

```
##### Create a Controller Package
Create a new package controller inside `com.example.learningspring`. Then, create a new class BookController

##### Create a Model Package
Create a new package model inside `com.example.learningspring`. Then, create a new class Book

##### Create a Model Class
Right click on the `model` package then
New -> Java Class -> Enter the Class Name
<!---
Once created the model class then select filed like,
```
private long id;
private String title;
private String authorName;
private String description;
private Boolean published;
```
##### Auto Generate setter and getter

THEN Right click on the inside the base package `Book model class` -> Select -> Generate setter and getter -> Select fields
--->
##### Create BookRepository to access data from the database
* Create a new package called repository inside the base package com.example.learningspring.
* Then, create an interface called `BookRepository` and extend it from JpaRepository.
```
package com.example.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningspring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

```
Then Spring Data JPA will generate implementation code for the most common CRUD operations – we don’t have to write a single query.

##### Book Service Class
Next, code a class that acts as a middle layer between persistence layer (repository) and controller layer. Create the `BookService` class with the following code:
```
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

```
##### RESTful API Endpoints

1. Create a Book

The following method a RESTful API that allows the clients to create a book

Code:
```
 @PostMapping(value = "books")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    Book _book = service.save(new Book(book.getTitle(), book.getAuthorName(), book.getDescription(), false));
    return new ResponseEntity<>(_book, HttpStatus.CREATED);
  }
```

Endpoint:
```
POST  http://localhost:8080/api/v1/books
```
Response:

```json
[
    {
        "id": 11,
        "title": "Spring Boot",
        "authorName": "Pivotal Team",
        "description": "Spring Boot is an open source Java-based framework used to create a micro Service",
        "published": false
    }
]
```

2. List all books
This method that returns a list of book (a kind of retrieval operation).
Code:
```
 @GetMapping(value = "books")
  public List<Book> getAllBooks(){
    return service.listAll();
  }
```

Endpoint:
```
GET  http://localhost:8080/api/v1/books
```
Response:
```json
[
    {
        "id": 11,
        "title": "Spring Boot",
        "authorName": "Pivotal Team",
        "description": "Spring Boot is an open source Java-based framework used to create a micro Service",
        "published": false
    },
    {
        "id": 12,
        "title": "Ruby",
        "authorName": "Yukihiro Matsumoto",
        "description": "Ruby is an interpreted, high-level, general-purpose programming language.",
        "published": false
    }
]
```
3. Get Book by ID
This method for a RESTful API that allows the clients to get information about a specific book based on ID.

Code:

```
  @GetMapping(value = "book/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    Book bookData = service.get(id);
    return new ResponseEntity<>(bookData, HttpStatus.OK);
  }
```
Endpoint:
```url
GET  http://localhost:8080/api/v1/book/11
```

Response:

```json
{
    "id": 11,
    "title": "Spring Boot",
    "authorName": "Pivotal Team",
    "description": "Spring Boot is an open source Java-based framework used to create a micro Service",
    "published": false
}
```

4. Update Book
The method that exposes RESTful API for update operation as follows.
Code:

```
 @PutMapping(value = "book/{id}")
  public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
    Book bookData = service.get(id);
    Book temp = service.update(bookData, book);
    return new ResponseEntity<>(temp, HttpStatus.OK);
  }
```

Endpoint:
```
PUT  http://localhost:8080/api/v1/book/11
```

Response:

```json
{
    "id": 11,
    "title": "Spring Boot",
    "authorName": "Pivotal Team",
    "description": "Spring Boot is an open source Java-based framework used to create a micro Service",
    "published": true
}
```

5. Delete a Book
The method that exposes RESTful API for the delete operation.
Code:
 
```
@DeleteMapping(value = "book/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
```
Endpoint:
```
DELETE  http://localhost:8080/api/v1/book/12
```

Response:
```json
[
    {
        "id": 11,
        "title": "Spring Boot",
        "authorName": "Pivotal Team",
        "description": "Spring Boot is an open source Java-based framework used to create a micro Service",
        "published": true
    }
]
```


</details>
