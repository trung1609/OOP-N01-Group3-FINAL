package com.example.qlsv_oop_group3.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {

    @Test
    void testGradeGettersSetters() {
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setMidtermScore(7.5);
        grade.setFinalScore(8.5);
        grade.setScore(8.0);
        grade.setSemester("20231");

        assertEquals(1L, grade.getId());
        assertEquals(7.5, grade.getMidtermScore());
        assertEquals(8.5, grade.getFinalScore());
        assertEquals(8.0, grade.getScore());
        assertEquals("20231", grade.getSemester());
    }

    @Test
    void testGradeEquality() {
        Grade g1 = new Grade();
        g1.setId(1L);
        g1.setMidtermScore(7.5);
        g1.setFinalScore(8.5);
        g1.setScore(8.0);
        g1.setSemester("20231");

        Grade g2 = new Grade();
        g2.setId(1L);
        g2.setMidtermScore(7.5);
        g2.setFinalScore(8.5);
        g2.setScore(8.0);
        g2.setSemester("20231");

    }
}
