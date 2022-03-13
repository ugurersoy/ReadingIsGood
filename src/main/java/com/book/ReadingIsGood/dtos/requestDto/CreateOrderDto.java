package com.book.ReadingIsGood.dtos.requestDto;

import lombok.Data;

@Data
public class CreateOrderDto {

    private String userId;
    private CreateBookRequestDto book;
}
