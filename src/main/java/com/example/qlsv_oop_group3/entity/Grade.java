package com.example.qlsv_oop_group3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grades")

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double midtermScore;
    private Double finalScore;
    private Double score;
    private String semester;
    @Column(name = "average_score")
    private Double averageScore;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
