package com.tanle.gscores.repository;

import com.tanle.gscores.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s.id, SUM(sc.score) " +
            "FROM Score sc " +
            "JOIN sc.student s " +
            "JOIN sc.subject sj " +
            "WHERE sj.id IN :subjectIds " +
            "GROUP BY s.id " +
            "ORDER BY SUM(sc.score) DESC")
    List<Object[]> findTop10GroupA(@Param("subjectIds") List<Long> subjectIds, Pageable pageable);
}
