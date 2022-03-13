package com.book.ReadingIsGood.controller;

import com.book.ReadingIsGood.enums.UserType;
import com.book.ReadingIsGood.dtos.requestDto.CreateUserDto;
import com.book.ReadingIsGood.dtos.responseDto.OrderDto;
import com.book.ReadingIsGood.service.OrderService;
import com.book.ReadingIsGood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final UserService userService;

    private final OrderService orderService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.userRegister(createUserDto, UserType.CUSTOMER);
    }

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<List<OrderDto>> getCustomerOrders(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getOrderListWithUserId(customerId));
    }

}
