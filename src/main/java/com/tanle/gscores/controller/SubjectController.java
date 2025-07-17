package com.tanle.gscores.controller;

import com.tanle.gscores.response.StatisticsResponse;
import com.tanle.gscores.response.SubjectResponse;
import com.tanle.gscores.service.StatisticsService;
import com.tanle.gscores.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectController {
    private final SubjectService subjectService;


    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponse>> getStatistics() {
        List<SubjectResponse> response = subjectService.findAll();
        return ResponseEntity.ok(response);
    }
}
