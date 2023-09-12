package com.example.repository;

import com.example.entity.Book;
import com.example.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepo extends JpaRepository<Shelf, Long> {


    @Override
    List<Shelf> findAll();

    @Override
    void delete(Shelf entity);

}
