package com.book.ReadingIsGood.dtos.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
public class BookDto {
    private String name;
    private Integer amount;
    private BigDecimal price;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createDate;
}
