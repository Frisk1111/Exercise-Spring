package com.example.repository;



import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


    @Override
    List<Book> findAll();

    @Override
    void delete(Book entity);


}
