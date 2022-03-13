package com.book.ReadingIsGood.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Book {

    @Id
    private String id;
    private String name;
    private Integer amount;
    private BigDecimal price;
    private String description;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updateDate;

}
