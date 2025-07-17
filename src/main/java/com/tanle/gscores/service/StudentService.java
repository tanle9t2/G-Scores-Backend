package com.tanle.gscores.service;

import com.tanle.gscores.entity.Student;
import com.tanle.gscores.response.StudentScoresResponse;
import com.tanle.gscores.response.TopStudentGroupAResponse;

import java.util.List;
import java.util.Set;

public interface StudentService {
    void saveBatchStudent(List<Student> student);

    void save(Student student);

    StudentScoresResponse findById(Long id);

    List<TopStudentGroupAResponse> findTop10GroupA();

}
