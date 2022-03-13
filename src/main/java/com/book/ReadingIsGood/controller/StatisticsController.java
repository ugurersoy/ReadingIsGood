package com.book.ReadingIsGood.controller;


import com.book.ReadingIsGood.dtos.responseDto.StatisticsDto;
import com.book.ReadingIsGood.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticsController {


    final private StatisticService statisticService;

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<List<StatisticsDto>> getStatics(@PathVariable String userId){
        return ResponseEntity.ok(statisticService.getUsersOrderStatistics(userId));
    }

}
