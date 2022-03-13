package com.book.ReadingIsGood.dtos.responseDto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class StatisticsDto {
    private String month;
    private Integer totalOrderCount;
    private BigDecimal totalAmountPurchased;
    private Integer totalBookCount;
}
