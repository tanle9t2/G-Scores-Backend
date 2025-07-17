package com.tanle.gscores.service;

import com.tanle.gscores.response.StatisticsResponse;

public interface StatisticsService {
    StatisticsResponse statisticsBySubjectId(Long subjectId);
}
