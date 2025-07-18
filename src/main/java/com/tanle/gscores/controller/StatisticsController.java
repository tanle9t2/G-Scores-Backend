package com.tanle.gscores.controller;

import com.tanle.gscores.response.StatisticsResponse;
import com.tanle.gscores.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(@RequestParam("subjectId") String subjectId) {
        StatisticsResponse response = statisticsService.statisticsBySubjectId(Long.parseLong(subjectId));
        return ResponseEntity.ok(response);
    }
}
