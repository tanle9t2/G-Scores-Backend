package com.tanle.gscores.repository;

import com.tanle.gscores.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("""
                SELECT 
                    s.subject,
                    CASE 
                        WHEN s.score >= 8 THEN '>=8'
                        WHEN s.score >= 6 THEN '6–7.99'
                        WHEN s.score >= 4 THEN '4–5.99'
                        ELSE '<4'
                    END,
                    COUNT(s)
                FROM Score s
                where s.subject.id=:subjectId
                GROUP BY s.subject,
                         CASE 
                             WHEN s.score >= 8 THEN '>=8'
                             WHEN s.score >= 6 THEN '6–7.99'
                             WHEN s.score >= 4 THEN '4–5.99'
                             ELSE '<4'
                         END
            """)
    List<Object[]> staticsScoreBySubjectId(@Param("subjectId") Long id);
}
