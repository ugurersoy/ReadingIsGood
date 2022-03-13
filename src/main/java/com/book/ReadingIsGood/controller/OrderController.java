package com.book.ReadingIsGood.controller;

import com.book.ReadingIsGood.dtos.requestDto.CreateOrderDto;
import com.book.ReadingIsGood.dtos.responseDto.OrderUserDto;
import com.book.ReadingIsGood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    final private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderDto createOrderDto){
        return ResponseEntity.ok(orderService.createOrder(createOrderDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderUserDto> getOrderById(@PathVariable String id){
       return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @GetMapping("/orders/{startDate}/{endDate}")
    public ResponseEntity<?> getOrderBetweenStartDateEndDate(@PathVariable String startDate,@PathVariable String endDate){
        return ResponseEntity.ok(orderService.getOrderListBetweenStartDateAndEndDate(startDate,endDate));
    }

}
