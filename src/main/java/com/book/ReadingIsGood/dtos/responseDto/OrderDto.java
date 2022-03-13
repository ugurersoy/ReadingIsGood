package com.book.ReadingIsGood.dtos.responseDto;


import com.book.ReadingIsGood.enums.OrderStatus;
import com.book.ReadingIsGood.model.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderDto {

    private String id;
    private List<Book> books;
    private OrderStatus saleStatus;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
