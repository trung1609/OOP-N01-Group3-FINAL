package com.example.qlsv_oop_group3.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setStudentCode("SV001");
        student.setFullName("Nguyen Van A");
        student.setEmail("nguyenvana@example.com");
        student.setAddress("Ha Noi");
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student.setGrades(new ArrayList<>());
    }

    @Test
    void testStudentCreation() {
        assertNotNull(student);
        assertEquals(1L, student.getId());
        assertEquals("SV001", student.getStudentCode());
        assertEquals("Nguyen Van A", student.getFullName());
        assertEquals("nguyenvana@example.com", student.getEmail());
        assertEquals("Ha Noi", student.getAddress());
        assertEquals(LocalDate.of(2000, 1, 1), student.getDateOfBirth());
        assertNotNull(student.getGrades());
        assertTrue(student.getGrades().isEmpty());
    }

    @Test
    void testAddGrade() {
        Grade grade = new Grade();
        grade.setId(1L);
        student.getGrades().add(grade);

        assertEquals(1, student.getGrades().size());
        assertTrue(student.getGrades().contains(grade));
    }

    @Test
    void testStudentEquality() {
        Student student2 = new Student();
        student2.setId(1L);
        student2.setStudentCode("SV001");
        student2.setFullName("Nguyen Van A");
        student2.setEmail("nguyenvana@example.com");
        student2.setAddress("Ha Noi");
        student2.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student2.setGrades(new ArrayList<>());

        assertEquals(student, student2);
        assertEquals(student.hashCode(), student2.hashCode());
    }

    @Test
    void testStudentAgeCalculation() {
        LocalDate birthDate = LocalDate.now().minusYears(20);
        student.setDateOfBirth(birthDate);
        assertEquals(20, student.getAge());

        student.setDateOfBirth(null);
        assertEquals(0, student.getAge());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        ArrayList<Grade> grades = new ArrayList<>();
        Student student = new Student(1L, "SV001", "Nguyen Van A",
                "nguyenvana@example.com", "Ha Noi",
                birthDate, grades);

        assertEquals(1L, student.getId());
        assertEquals("SV001", student.getStudentCode());
        assertEquals("Nguyen Van A", student.getFullName());
        assertEquals("nguyenvana@example.com", student.getEmail());
        assertEquals("Ha Noi", student.getAddress());
        assertEquals(birthDate, student.getDateOfBirth());
        assertEquals(grades, student.getGrades());
    }

    @Test
    void testNoArgsConstructor() {
        Student student = new Student();
        assertNotNull(student);
    }
}