package com.example.qlsv_oop_group3.repo;

import com.example.qlsv_oop_group3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    boolean existsByStudentCode(String studentCode);
    boolean existsByEmail(String email);
    //Tìm sv theo điểm tb của 1 môn học cụ thể
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.course.id = :courseId AND g.averageScore >= :minScore")
    List<Student> findStudentsByCourseIdAndMinScore(@Param("courseId") Long courseId, @Param("minScore") Double minScore);
    //Tìm sv chưa có điểm của 1 môn học
    @Query("select s from Student s Where s.id NOT IN (Select g.student.id From Grade g Where g.course.id = :courseId)")
    List<Student> findStudentsWithNoCourseGrade(@Param("courseId") Long courseId);
    //Tìm sv theo học kỳ
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.semester = :semester")
    List<Student> findStudentsBySemester(@Param("semester") String semester);
}
