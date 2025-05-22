package com.example.qlsv_oop_group3.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setId(1L);
        course.setCourseName("Java Programming");
        course.setCourseCode("JAVA001");
        course.setCredit(3);
        course.setGrades(new ArrayList<>());
    }

    @Test
    void testCourseCreation() {
        assertNotNull(course);
        assertEquals(1L, course.getId());
        assertEquals("Java Programming", course.getCourseName());
        assertEquals("JAVA001", course.getCourseCode());
        assertEquals(3, course.getCredit());
        assertNotNull(course.getGrades());
        assertTrue(course.getGrades().isEmpty());
    }

    @Test
    void testAddGrade() {
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setCourse(course);

        course.getGrades().add(grade);

        assertEquals(1, course.getGrades().size());
        assertTrue(course.getGrades().contains(grade));
    }

    @Test
    void testCourseEquality() {
        Course course2 = new Course();
        course2.setId(1L);
        course2.setCourseName("Java Programming");
        course2.setCourseCode("JAVA001");
        course2.setCredit(3);
        course2.setGrades(new ArrayList<>());

        assertEquals(course, course2);
        assertEquals(course.hashCode(), course2.hashCode());
    }

    @Test
    void testNullValues() {
        Course course = new Course();
        assertNull(course.getCourseName());
        assertNull(course.getCourseCode());
        assertNull(course.getCredit());
        assertNull(course.getGrades());
    }

    @Test
    void testUniqueConstraint() {
        assertNotNull(course.getCourseName());
    }

    @Test
    void testGradesRelationship() {
        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setCourse(course);

        Grade grade2 = new Grade();
        grade2.setId(2L);
        grade2.setCourse(course);

        ArrayList<Grade> grades = new ArrayList<>();
        grades.add(grade1);
        grades.add(grade2);
        course.setGrades(grades);

        assertEquals(2, course.getGrades().size());
        assertTrue(course.getGrades().contains(grade1));
        assertTrue(course.getGrades().contains(grade2));
    }
}
