
package com.example.qlsv_oop_group3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String courseName;
    private String courseCode;
    private Integer credit;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Grade> grades;

    // Default constructor
    public Course() {
    }

    // constructor
    public Course(long id, String courseName, String courseCode, Integer credit, List<Grade> grades) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.grades = grades;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}