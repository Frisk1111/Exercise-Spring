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

    public ResponseEntity<?> addBook(BookDto bookDto) {


        List<Book> book2 = bookRepo.findAll();


        if (bookDto != null) {
            Book book = new Book();


            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setIsbn(bookDto.getIsbn());
            book.setStatus(bookDto.getStatus());

            for (Book book1 : book2) {

                if (book1.getIsbn().equals(book.getIsbn())) {


                    return ResponseEntity.badRequest().body("\n SOMETHING HAPPEND! THE ISBN SEEMS TO  BE EQUALS to ANOTHER BOOk! CHANGE IT :((");


                }
            }


            bookRepo.save(book);


            bookDto.setTitle(book.getTitle());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setStatus(book.getStatus());
            bookDto.setId(book.getId());

            return ResponseEntity.ok().body(bookDto);
        } else {

            throw new RuntimeException("SOMETHING HAPPEND! SOMETHING NOT GOOD. so sorry :(");
        }
    }


    public BookDto updateBook(Long id, BookDto bookDto) {

        Book book;

        Optional<Book> optionalBook = bookRepo.findById(id);

        if (optionalBook.isPresent()) {

            book = optionalBook.get();

            bookRepo.delete(book);


            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setIsbn(bookDto.getIsbn());
            book.setStatus(bookDto.getStatus());

            bookRepo.save(book);


            bookDto.setTitle(book.getTitle());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setStatus(book.getStatus());


            return bookDto;
        } else {

            throw new RuntimeException("Book no0t found");
        }


    }

    public void physicalDeleteBookById(Long id) {

        Book book;
        Optional<Book> optionalBook = bookRepo.findById(id);

        if (optionalBook.isPresent()) {

            book = optionalBook.get();

            bookRepo.delete(book);
        } else {

            throw new RuntimeException("Book no0t found");
        }


    }


    public void logicalDeleteBookBYId(Long id) {

        Book book;

        Optional<Book> optionalBook = bookRepo.findById(id);


        if (optionalBook.isPresent()) {


            book = optionalBook.get();


            book.setStatus(StatusEnum.SELLED);

            bookRepo.save(book);

        }


    }

}
