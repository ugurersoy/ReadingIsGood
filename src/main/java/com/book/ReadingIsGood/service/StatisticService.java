package com.book.ReadingIsGood.service;

import com.book.ReadingIsGood.enums.OrderStatus;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.model.entity.Order;
import com.book.ReadingIsGood.dtos.responseDto.StatisticsDto;
import com.book.ReadingIsGood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    final private OrderRepository orderRepository;


    public List<StatisticsDto> getUsersOrderStatistics(String userId) {
        List<Order> list = orderRepository.findOrdersByUserId(userId);
        List<StatisticsDto> listStatistics = new ArrayList<>();

        for (Order it : list) {
            String month = it.getCreateDate().getMonth().name();

            if (!listStatistics.isEmpty() && listStatistics.stream().anyMatch(lst -> lst.getMonth().equals(month))) {
                continue;
            }

            List<Order> orderLst = list.stream().filter(orderIt -> orderIt.getCreateDate().getMonth().name().equals(month)).collect(Collectors.toList());

            Integer orderedCount = 0;
            BigDecimal bookPrice = BigDecimal.ZERO;

            for (Order order: orderLst)
            {
                if(order.getSaleStatus().equals(OrderStatus.SOLD)){
                    orderedCount++;
                    bookPrice.add(order.getBook().getPrice());
                }
            }

            StatisticsDto statisticsDto = new StatisticsDto();
            statisticsDto.setTotalOrderCount(orderedCount);
            statisticsDto.setMonth(month);
            statisticsDto.setTotalBookCount(orderLst.size());
            statisticsDto.setTotalAmountPurchased(bookPrice);
            listStatistics.add(statisticsDto);
        }

        return listStatistics;
    }


}
