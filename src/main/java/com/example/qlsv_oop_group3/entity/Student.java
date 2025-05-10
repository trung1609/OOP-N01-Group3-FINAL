package com.example.qlsv_oop_group3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private  String fullName;
    private  String studentCode;
    private  String email;
    private String address;
    private LocalDate dateOfBirth;

    @OneToMany (mappedBy = "student")
    private  java.util.List<Grade> grades;
}
