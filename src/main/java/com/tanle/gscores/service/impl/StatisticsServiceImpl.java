package com.tanle.gscores.service.impl;

import com.tanle.gscores.entity.Subject;
import com.tanle.gscores.repository.ScoreRepository;
import com.tanle.gscores.response.StatisticsResponse;
import com.tanle.gscores.response.StudentScoresResponse;
import com.tanle.gscores.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final ScoreRepository scoreRepository;

    @Override
    public StatisticsResponse statisticsBySubjectId(Long subjectId) {
        List<Object[]> objects = scoreRepository.staticsScoreBySubjectId(subjectId);
        Subject subject = (Subject) objects.get(0)[0];
        Map<String, Integer> order = Map.of(
                ">=8", 1,
                "6–7.99", 2,
                "4–5.99", 3,
                "<4", 4
        );

        List<StatisticsResponse.Grade> grade = objects.stream()
                .map(o -> StatisticsResponse.Grade.builder()
                        .count((Long) o[2])
                        .score((String) o[1])
                        .build())
                .sorted(Comparator.comparing(item -> order.get(item.getScore())))
                .collect(Collectors.toList());
        StatisticsResponse statisticsResponse = StatisticsResponse.builder()
                .name(subject.getName())
                .grades(grade)
                .build();
        return statisticsResponse;
    }
}
