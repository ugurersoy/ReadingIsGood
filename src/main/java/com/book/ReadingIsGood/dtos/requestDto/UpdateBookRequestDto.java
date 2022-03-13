package com.book.ReadingIsGood.dtos.requestDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateBookRequestDto {
    private Integer amount;
}
