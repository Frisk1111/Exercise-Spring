package com.example.dto;


import com.example.entity.Book;
import com.example.entity.Shelf;
import com.example.enums.StatusEnum;

import java.util.List;

public class BookDto {


    private Long id;


    private String title;

    private String author;

    private String isbn;

    private StatusEnum status;

    private Shelf shelf;

    public BookDto(String title, String author, String isbn, StatusEnum status, Shelf shelf) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
        this.shelf = shelf;
    }

    public BookDto(Long id, String title, String author, String isbn, StatusEnum status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public BookDto() {
    }

    public BookDto(List<Book> books) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
}
