package com.book.ReadingIsGood.dtos.requestDto;

import lombok.Data;



@Data
public class CreateUserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
