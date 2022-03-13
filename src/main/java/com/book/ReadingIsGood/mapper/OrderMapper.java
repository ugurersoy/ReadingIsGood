package com.book.ReadingIsGood.mapper;


import com.book.ReadingIsGood.dtos.requestDto.CreateOrderKafkaDto;
import com.book.ReadingIsGood.model.entity.Order;
import com.book.ReadingIsGood.dtos.responseDto.OrderDto;
import com.book.ReadingIsGood.dtos.responseDto.OrderUserDto;
import org.mapstruct.Mapper;

 @Mapper(componentModel = "spring")
 public interface OrderMapper {
      OrderDto toOrderDtoFromOrder(Order order);
      OrderUserDto toOrderUserDtoFromOrder(Order order);
      Order toOrder(CreateOrderKafkaDto createOrderKafkaDto);

 }
