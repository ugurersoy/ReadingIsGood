package com.book.ReadingIsGood.service;

import com.book.ReadingIsGood.dtos.requestDto.CreateOrderKafkaDto;
import com.book.ReadingIsGood.dtos.responseDto.BookDto;
import com.book.ReadingIsGood.enums.OrderStatus;
import com.book.ReadingIsGood.exceptions.BookNotFoundException;
import com.book.ReadingIsGood.exceptions.UserNotFoundException;
import com.book.ReadingIsGood.mapper.OrderMapper;
import com.book.ReadingIsGood.dtos.requestDto.CreateOrderDto;
import com.book.ReadingIsGood.dtos.responseDto.OrderDto;
import com.book.ReadingIsGood.dtos.responseDto.OrderUserDto;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.model.entity.Order;
import com.book.ReadingIsGood.model.entity.User;
import com.book.ReadingIsGood.repository.BookRepository;
import com.book.ReadingIsGood.repository.OrderRepository;
import com.book.ReadingIsGood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    final private OrderRepository orderRepository;

    final private OrderMapper orderMapper;

    final private KafkaTemplate<String,CreateOrderKafkaDto> kafkaTemplate;

    final private UserRepository userRepository;

    final private BookRepository bookRepository;


    public List<OrderDto> getOrderListWithUserId(String userId){

         List<OrderDto> orderList = orderRepository
                 .findOrdersByUserId(userId)
                 .stream()
                 .map(orderMapper::toOrderDtoFromOrder)
                 .collect(Collectors.toList());

        return orderList;
    }


    public OrderUserDto getOrderById(String id)
    {
        return orderMapper.toOrderUserDtoFromOrder(orderRepository.findById(id).get());
    }


    public List<OrderDto> getOrderListBetweenStartDateAndEndDate(String startDate,String endDate){
        LocalDateTime createDate = stringToLocalDate(startDate);
        LocalDateTime updateDate = stringToLocalDate(endDate);

        List<OrderDto> orders =  orderRepository
                .findOrdersByCreateDateAfterAndUpdateDateBefore(createDate,updateDate)
                .stream()
                .map(orderMapper::toOrderDtoFromOrder)
                .collect(Collectors.toList());

        return orders;
    }

    private LocalDateTime stringToLocalDate(String stringDate)
    {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
      return LocalDateTime.parse(stringDate, formatter);
    }


    public String createOrder(CreateOrderDto createOrderDto) {

        User user = userRepository.findById(createOrderDto.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        CreateOrderKafkaDto order = new CreateOrderKafkaDto();
        order.setUser(user);
        order.setBook(bookRepository.findByName(createOrderDto.getBook().getName()).orElseThrow(()-> new BookNotFoundException("Kitap Bulunamadı")));
        order.setSaleStatus(OrderStatus.ON_PROCESS);
        orderRepository.save(orderMapper.toOrder(order));

        kafkaTemplate.send("booksorder",order);
        return "Your order process started";
    }

    @KafkaListener(topics = "booksorder",groupId = "book")
    private void KafkaListener(CreateOrderKafkaDto data){

        Order order = new Order();
        Book book = data.getBook();

        if(bookRepository.existsByAmountGreaterThanAndName(0,book.getName())){
            book.setAmount(book.getAmount()-1);
            bookRepository.save(book);
            order.setSaleStatus(OrderStatus.SOLD);
        }else {
            order.setSaleStatus(OrderStatus.UNSOLD);
        }
        order.setUser(data.getUser());
        order.setBook(book);
        order.setUpdateDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}
