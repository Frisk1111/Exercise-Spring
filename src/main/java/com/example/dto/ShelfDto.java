package com.example.dto;


import com.example.entity.Book;
import com.example.enums.StatusShelf;

import java.util.List;

public class ShelfDto {


    private Long id;


    private String category;

    private List<BookDto> books;

    private StatusShelf statusShelf;


    public ShelfDto() {
    }

    public ShelfDto(String category, List<BookDto> books, StatusShelf statusShelf) {
        this.category = category;
        this.books = books;
        this.statusShelf = statusShelf;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusShelf getStatusShelf() {
        return statusShelf;
    }

    public void setStatusShelf(StatusShelf statusShelf) {
        this.statusShelf = statusShelf;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
