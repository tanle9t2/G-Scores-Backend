package com.tanle.gscores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private Long sbd;

    private String fLanguageCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL.ALL)
    private List<Score> scores = new ArrayList<>();
}
