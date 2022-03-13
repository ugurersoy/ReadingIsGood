package com.book.ReadingIsGood.dtos.responseDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private String name;
    private String surname;
    private String email;
}
