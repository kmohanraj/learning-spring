### Learning Spring Boot

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

##### Create BookRepository to access data from the database
Create a new package called repository inside the base package com.example.learningspring. Then, create an interface called `BookRepository` and extend it from JpaRepository.
```
package com.example.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.learningspring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

```


</details>
