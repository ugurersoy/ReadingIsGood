package com.book.ReadingIsGood.repository;

import com.book.ReadingIsGood.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
