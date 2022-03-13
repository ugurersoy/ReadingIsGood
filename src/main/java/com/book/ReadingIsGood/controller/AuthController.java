package com.book.ReadingIsGood.controller;


import com.book.ReadingIsGood.enums.UserType;
import com.book.ReadingIsGood.dtos.requestDto.CreateUserDto;
import com.book.ReadingIsGood.dtos.requestDto.LoginRequestDto;
import com.book.ReadingIsGood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    final private UserService  userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
      return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
       return userService.userRegister(createUserDto,UserType.ADMIN);
    }

}
