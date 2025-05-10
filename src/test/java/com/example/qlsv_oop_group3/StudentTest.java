package com.example.qlsv_oop_group3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentTest {
    @Autowired
    private StudentRepo studentRepository;

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setStudentCode("SV001");
        student.setFullName("Nguyen Van A");
        student.setEmail("nva@example.com");
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student.setAddress("Ha Noi");

        Student savedStudent = studentRepository.save(student);
        assertNotNull(savedStudent.getId());
        assertEquals("SV001", savedStudent.getStudentCode());
    }
}

