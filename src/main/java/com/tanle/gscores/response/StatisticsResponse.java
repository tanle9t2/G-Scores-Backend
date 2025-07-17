package com.tanle.gscores.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {
    private int id;
    private String name;
    private List<Grade> grades;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Grade {
        private String score;
        private long count;
    }
}
