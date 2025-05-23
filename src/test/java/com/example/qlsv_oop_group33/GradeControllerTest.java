package com.example.qlsv_oop_group3.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {
    private Grade grade;
    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        grade = new Grade();
        student = new Student();
        course = new Course();

        // Setup student
        student.setId(1L);
        student.setStudentCode("SV001");
        student.setFullName("Nguyen Van A");

        // Setup course
        course.setId(1L);
        course.setCourseName("Java Programming");
        course.setCourseCode("JAVA001");
        course.setCredit(3);

        // Setup grade
        grade.setId(1L);
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setMidtermScore(8.5);
        grade.setFinalScore(9.0);
        grade.setScore(8.5);
        grade.setSemester("Spring 2024");
        grade.setAverageScore(8.75);
    }

    @Test
    void testGradeCreation() {
        assertNotNull(grade);
        assertEquals(1L, grade.getId());
        assertEquals(student, grade.getStudent());
        assertEquals(course, grade.getCourse());
        assertEquals(8.5, grade.getMidtermScore());
        assertEquals(9.0, grade.getFinalScore());
        assertEquals(8.5, grade.getScore());
        assertEquals("Spring 2024", grade.getSemester());
        assertEquals(8.75, grade.getAverageScore());
    }

    @Test
    void testGradeEquality() {
        Grade grade2 = new Grade();
        grade2.setId(1L);
        grade2.setStudent(student);
        grade2.setCourse(course);
        grade2.setMidtermScore(8.5);
        grade2.setFinalScore(9.0);
        grade2.setScore(8.5);
        grade2.setSemester("Spring 2024");
        grade2.setAverageScore(8.75);

        assertEquals(grade, grade2);
        assertEquals(grade.hashCode(), grade2.hashCode());
    }

    @Test
    void testNullScores() {
        Grade grade = new Grade();
        assertNull(grade.getMidtermScore());
        assertNull(grade.getFinalScore());
        assertNull(grade.getScore());
        assertNull(grade.getAverageScore());
    }

    @Test
    void testRelationships() {
        assertNotNull(grade.getStudent());
        assertNotNull(grade.getCourse());
        assertEquals("Nguyen Van A", grade.getStudent().getFullName());
        assertEquals("Java Programming", grade.getCourse().getCourseName());
    }
}