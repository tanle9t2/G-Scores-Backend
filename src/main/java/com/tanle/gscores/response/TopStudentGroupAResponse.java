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
public class TopStudentGroupAResponse {
    private Long id;
    private double total;
}
