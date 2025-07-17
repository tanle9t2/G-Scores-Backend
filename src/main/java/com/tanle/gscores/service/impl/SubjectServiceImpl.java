package com.tanle.gscores.service.impl;

import com.tanle.gscores.entity.Subject;
import com.tanle.gscores.repository.SubjectRepository;
import com.tanle.gscores.response.SubjectResponse;
import com.tanle.gscores.service.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectResponse> findAll() {
        List<SubjectResponse> subjectResponses = subjectRepository.findAll().stream()
                .map(s -> SubjectResponse.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build())
                .collect(Collectors.toList());

        return subjectResponses;
    }
}
