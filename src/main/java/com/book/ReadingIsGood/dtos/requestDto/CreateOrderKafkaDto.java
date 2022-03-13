package com.book.ReadingIsGood.dtos.requestDto;

import com.book.ReadingIsGood.enums.OrderStatus;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderKafkaDto {

    private User user;
    private Book book;
    private OrderStatus saleStatus;
}
