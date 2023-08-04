package com.example.service;



import com.example.dto.BookDto;
import com.example.entity.Book;
import com.example.enums.StatusEnum;
import com.example.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public List<BookDto> findAllBook() {

        List<Book> bookList = bookRepo.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : bookList) {

            bookDtoList.add(new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getStatus()));

        }

        return bookDtoList;
    }


    public ResponseEntity<?> findById(Long id) {

        Optional<Book> optionalBook = bookRepo.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();


            if (book.getStatus() != StatusEnum.SELLED) {
                return ResponseEntity.ok().body(new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn()));


            } else{

                return ResponseEntity.badRequest().body("\n THIS BOOK HAS BEEN SELLED! Sorry.");
            }
        } else {

            return ResponseEntity.badRequest().body("Book no0t found");
        }

    }



}
