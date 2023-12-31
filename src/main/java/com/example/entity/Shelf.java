package com.example.entity;

import com.example.enums.StatusShelf;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shelf {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String category;


    @OneToMany(mappedBy = "shelf")
    private List<Book> books;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusShelf statusShelf;

    public Shelf() {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
