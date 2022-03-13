package com.book.ReadingIsGood.model.entity;

import com.book.ReadingIsGood.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {

    @Id
    private String id;

    private User user;
    private Book book;
    private OrderStatus saleStatus;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;


}
