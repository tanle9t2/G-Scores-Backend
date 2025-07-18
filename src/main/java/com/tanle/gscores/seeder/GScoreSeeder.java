package com.tanle.gscores.seeder;

import com.tanle.gscores.entity.Score;
import com.tanle.gscores.entity.Student;
import com.tanle.gscores.entity.Subject;
import com.tanle.gscores.repository.StudentRepository;
import com.tanle.gscores.repository.SubjectRepository;
import com.tanle.gscores.service.StudentService;
import com.tanle.gscores.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GScoreSeeder implements CommandLineRunner {

    private final StudentService studentService;
    private final SubjectService subjectService;
    private static final int BATCH_SIZE = 1000;

    @Override
    public void run(String... args) throws Exception {
        File file = new File("/app/dataset/diem_thi_thpt_2024.csv");
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }
        InputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String headerLine = reader.readLine();
        String[] headers = headerLine.split(",");

        // Cache for subjects
        Map<String, Subject> subjectCache = new HashMap<>();

        for (int i = 1; i < headers.length - 1; i++) {
            String subjectName = headers[i].trim();
            Subject subject = Subject.builder()
                    .name(subjectName)
                    .build();
            subjectService.saveSubject(subject);
            subjectCache.put(subjectName, subject);
        }

        String line;
        int count = 0;
        List<Student> batch = new ArrayList<>();
        long start = System.currentTimeMillis();
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String sbd = tokens[0];

            String fLanguageCode = null;
            int length = tokens.length;
            if (tokens.length == 11) {
                fLanguageCode = tokens[10];
                length -= 1;
            }


            Student student = Student.builder()
                    .id(Long.parseLong(sbd))
                    .fLanguageCode(fLanguageCode)
                    .build();

            for (int i = 1; i < length; i++) {
                if (!tokens[i].trim().isEmpty()) {
                    Score score = Score.builder()
                            .score(Double.parseDouble(tokens[i]))
                            .subject(subjectCache.get(headers[i]))
                            .build();
                    student.addScore(score);
                }
            }
            batch.add(student);
            count++;
            if (batch.size() == BATCH_SIZE) {
                studentService.saveBatchStudent(batch); // Fast bulk insert
                batch.clear();
                System.out.println("Inserted: " + count);
            }
        }

        if (!batch.isEmpty()) {
            studentService.saveBatchStudent(batch); // Fast bulk insert
            batch.clear();
        }

        long end = System.currentTimeMillis();
        System.out.println("⏱ Total time: " + (end - start) + " ms");
        System.out.println("✅ Seeding completed! Total: " + count);
    }

}
