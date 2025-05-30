package com.example.qlsv_oop_group3.dto;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.entity.Student;

public class GradeDTO {
    private long id;
    private Double midtermScore;
    private Double finalScore;
    private Double score;
    private String semester;
    private Double averageScore;
    private Long studentId;
    private String studentCode;
    private String studentName;
    private Long courseId;
    private String courseCode;
    private String courseName;
    private Integer courseCredit;

    // Default constructor
    public GradeDTO() {
    }

    // Constructor from Grade entity
    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.midtermScore = grade.getMidtermScore();
        this.finalScore = grade.getFinalScore();
        this.score = grade.getScore();
        this.semester = grade.getSemester();
        this.averageScore = grade.getAverageScore();

        if (grade.getStudent() != null) {
            Student student = grade.getStudent();
            this.studentId = student.getId();
            this.studentCode = student.getStudentCode();
            this.studentName = student.getFullName();
        }

        if (grade.getCourse() != null) {
            Course course = grade.getCourse();
            this.courseId = course.getId();
            this.courseCode = course.getCourseCode();
            this.courseName = course.getCourseName();
            this.courseCredit = course.getCredit();
        }
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(Double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }
}
