package com.tanle.gscores.response;

import com.tanle.gscores.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentScoresResponse {
    private Long id;
    private String fLanguageCode;
    private List<ScoreResponse> scores;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScoreResponse {
        private Double score;
        private String subject;
    }
}
