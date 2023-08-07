package com.example.controller;



import com.example.dto.BookDto;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {

        return bookService.findById(id);

    }

    @GetMapping()
    public List<BookDto> findAllBooks() {

        return bookService.findAllBook();

    }

    @PostMapping()
    public ResponseEntity<?> addBooks(@RequestBody BookDto bookDto) {

        return bookService.addBook(bookDto);
    }



    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {

        return bookService.updateBook(id, bookDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {

        bookService.physicalDeleteBookById(id);

        return ResponseEntity.ok().body("\n  --  BOOK DELTED !!! -- \n");
    }


    @DeleteMapping("/logicalDelete/{id}")
    public ResponseEntity<?> logicalDeleteById(@PathVariable Long id){


         bookService.logicalDeleteBookBYId(id);
        return ResponseEntity.ok().body("\n BOOK STATUS : SELLED !!");
    }



}
