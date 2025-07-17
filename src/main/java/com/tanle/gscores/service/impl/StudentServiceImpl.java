package com.tanle.gscores.service.impl;

import com.tanle.gscores.entity.Student;
import com.tanle.gscores.entity.Subject;
import com.tanle.gscores.exception.ResourceNotFoundException;
import com.tanle.gscores.repository.StudentRepository;
import com.tanle.gscores.repository.SubjectRepository;
import com.tanle.gscores.response.StudentScoresResponse;
import com.tanle.gscores.response.TopStudentGroupAResponse;
import com.tanle.gscores.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public void saveBatchStudent(List<Student> student) {
        studentRepository.saveAll(student);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public StudentScoresResponse findById(Long id) {
        StudentScoresResponse response = studentRepository.findById(id)
                .map(s -> {
                    List<StudentScoresResponse.ScoreResponse> scoreResponses = s.getScores().stream()
                            .map(score -> StudentScoresResponse.ScoreResponse.builder()
                                    .subject(score.getSubject().getName())
                                    .score(score.getScore())
                                    .build())
                            .collect(Collectors.toList());

                    return StudentScoresResponse.builder()
                            .id(s.getId())
                            .fLanguageCode(s.getFLanguageCode())
                            .scores(scoreResponses)
                            .build();
                }).orElseThrow(() -> new ResourceNotFoundException("Not found student: " + id));
        return response;
    }

    @Override
    public List<TopStudentGroupAResponse> findTop10GroupA() {
        Long mathId = subjectRepository.findByName("toan")
                .map(Subject::getId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Math"));
        Long physics = subjectRepository.findByName("vat_li")
                .map(Subject::getId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Physics"));
        Long chemistry = subjectRepository.findByName("hoa_hoc")
                .map(Subject::getId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Chemistry"));
        List<Object[]> objects = studentRepository
                .findTop10GroupA(List.of(mathId, physics, chemistry), PageRequest.of(0, 10));

        return objects.stream().map(o -> TopStudentGroupAResponse.builder()
                        .id((Long) o[0])
                        .total((Double) o[1])
                        .build())
                .collect(Collectors.toList());
    }
}
