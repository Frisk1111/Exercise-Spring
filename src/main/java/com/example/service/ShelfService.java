package com.example.service;


import com.example.dto.BookDto;
import com.example.dto.ShelfDto;
import com.example.entity.Book;
import com.example.entity.Shelf;
import com.example.enums.StatusShelf;
import com.example.exception.BadRequestException;
import com.example.exception.NotFoundException;
import com.example.repository.ShelfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    private ShelfRepo shelfRepo;


    @Autowired
    public ShelfService(ShelfRepo shelfRepo) {
        this.shelfRepo = shelfRepo;
    }


    public List<ShelfDto> findAllShelfs() {

        List<Shelf> shelfList = shelfRepo.findAll();

        List<ShelfDto> shelfDtoList = new ArrayList<>();


        for (Shelf shelf1 : shelfList) {

            List<BookDto> bookDtoList = new ArrayList<>();

            bookDtoList.add(new BookDto(shelf1.getBooks()));

            for (Shelf shelf : shelfList) {

                shelfDtoList.add(new ShelfDto(shelf.getCategory(), bookDtoList, shelf.getStatusShelf()));
            }


        }

        return shelfDtoList;


    }


    public ShelfDto findOneShelfByItsId(Long id) throws NotFoundException {

        Optional<Shelf> shelf = shelfRepo.findById(id);

        Shelf shelf1 = new Shelf();

        List<Book> bookList = new ArrayList<>();

        List<BookDto> bookDtoList = new ArrayList<>();

        ShelfDto shelfDto = new ShelfDto();

        if (shelf.isPresent()) {

            shelf1 = shelf.get();

            bookList.add((Book) shelf1.getBooks());


            for (Book book : bookList) {

                bookDtoList.add(new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getStatus(), book.getShelf()));


            }


            shelfDto.setBooks(bookDtoList);
            shelfDto.setCategory(shelf1.getCategory());
            shelfDto.setStatusShelf(shelf1.getStatusShelf());

            return shelfDto;

        } else {
            throw new NotFoundException("Its seems that this shelf doesn't exits!");
        }


    }



    public ResponseEntity<?> addAShelf(ShelfDto shelfdto){


        if(shelfdto != null){

            List<BookDto> bookDtoList = new ArrayList<>();

            List<Book> bookList = new ArrayList<>();

            Shelf shelf = new Shelf();


            bookDtoList.add((BookDto) shelfdto.getBooks());

            for (BookDto bookDto : bookDtoList) {

                bookList.add(new Book(bookDto.getAuthor(), bookDto.getAuthor(),bookDto.getIsbn(), bookDto.getStatus(), bookDto.getShelf()));
            }


            shelf.setCategory(shelfdto.getCategory());
            shelf.setBooks(bookList);
            shelf.setStatusShelf(shelfdto.getStatusShelf());


            shelfRepo.save(shelf);


            for (Book book : bookList) {

                bookDtoList.add(new BookDto(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getStatus(), book.getShelf()));


            }

            shelfdto.setCategory(shelf.getCategory());
            shelfdto.setBooks(bookDtoList);
            shelfdto.setStatusShelf(shelf.getStatusShelf());


            return  ResponseEntity.ok().body(shelfdto);


        } else {

            throw new RuntimeException("This shelf is empty!");
        }

    }



    public ResponseEntity<?> logicalDeleteShelfbyItsId(Long id){


        Optional<Shelf> optionalShelf = shelfRepo.findById(id);

        Shelf shelf = new Shelf();

        if(optionalShelf.isPresent()){

            shelf = optionalShelf.get();

            shelf.setStatusShelf(StatusShelf.EMPTY);

            return ResponseEntity.ok().body(" -- STATUS SET TO EMPTY --");

        } else{

            throw new BadRequestException("This shelf isn't present !");
        }

    }


    public ResponseEntity<?> physicalDeleteShelfByItsId(Long id){

        Optional<Shelf> optionaleShelf = shelfRepo.findById(id);

        Shelf shelf = new Shelf();


        if(optionaleShelf.isPresent()){


            shelf = optionaleShelf.get();

            shelfRepo.delete(shelf);


            return ResponseEntity.ok().body(" --- SHELF DELETED ---");
        }
        else{

            throw new BadRequestException("- - It seems that the id isn't valid! :( - - \n Change it ! ");
        }

    }
}
