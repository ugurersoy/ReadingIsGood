package com.book.ReadingIsGood.dtos.responseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    String token;
    String id;
    String email;
    String role;
}
