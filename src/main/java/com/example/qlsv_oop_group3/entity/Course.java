package com.example.qlsv_oop_group3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String courseName;
    private String courseCode;
    private Integer credit;

    @OneToMany(mappedBy = "course")
    private List<Grade> grades;
}
