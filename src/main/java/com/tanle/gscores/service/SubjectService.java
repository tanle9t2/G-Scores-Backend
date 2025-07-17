package com.tanle.gscores.service;

import com.tanle.gscores.entity.Subject;
import com.tanle.gscores.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    void saveSubject(Subject subject);
    List<SubjectResponse> findAll();
}
