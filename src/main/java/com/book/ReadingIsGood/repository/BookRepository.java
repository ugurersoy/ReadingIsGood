package com.book.ReadingIsGood.repository;

import com.book.ReadingIsGood.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,String> {

    Optional<Book> findByName(String name);
    Boolean existsByAmountGreaterThanAndName(Integer amount,String name);


}
