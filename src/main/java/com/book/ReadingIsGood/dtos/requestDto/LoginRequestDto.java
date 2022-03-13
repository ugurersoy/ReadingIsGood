package com.book.ReadingIsGood.dtos.requestDto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
