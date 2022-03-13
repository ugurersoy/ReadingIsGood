package com.book.ReadingIsGood.repository;

import com.book.ReadingIsGood.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public  interface  OrderRepository  extends MongoRepository<Order,String> {
    List<Order> findOrdersByUserId(String id);

    List<Order> findOrdersByCreateDateAfterAndUpdateDateBefore(LocalDateTime createDate,LocalDateTime updateDate);
}
