package com.tanle.gscores.controller;

import com.tanle.gscores.response.StudentScoresResponse;
import com.tanle.gscores.response.TopStudentGroupAResponse;
import com.tanle.gscores.service.StudentService;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/student/{id}")
    public ResponseEntity<StudentScoresResponse> getByStudentId(@PathVariable("id") String id) {
        StudentScoresResponse response = studentService.findById(Long.parseLong(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/top")
    public ResponseEntity<List<TopStudentGroupAResponse>> getTop10GroupA() {
        List<TopStudentGroupAResponse> response = studentService.findTop10GroupA();
        return ResponseEntity.ok(response);
    }
}
