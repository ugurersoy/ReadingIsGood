package com.book.ReadingIsGood.model.entity;

import com.book.ReadingIsGood.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType userType;
    private Integer purchaseLimit;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
