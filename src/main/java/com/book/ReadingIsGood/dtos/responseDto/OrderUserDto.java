package com.book.ReadingIsGood.dtos.responseDto;

import com.book.ReadingIsGood.enums.OrderStatus;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.model.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderUserDto {

    private String id;
    private User user;
    private List<Book> books;
    private OrderStatus saleStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
