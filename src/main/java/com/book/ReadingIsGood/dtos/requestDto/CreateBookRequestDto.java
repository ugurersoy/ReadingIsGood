package com.book.ReadingIsGood.dtos.requestDto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CreateBookRequestDto {
    private String name;
    private Integer amount;
    private BigDecimal price;
    private String description;
}
