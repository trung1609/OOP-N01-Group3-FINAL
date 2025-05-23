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
    // Tìm sinh viên theo mã sinh viên
    Optional<Student> findByStudentCode(String studentCode);

    // Tìm sinh viên theo email
    Optional<Student> findByEmail(String email);

    // Tìm sinh viên theo tên (không phân biệt hoa thường)
    List<Student> findAllByFullNameContainingIgnoreCase(String fullName);

    // Kiểm tra tồn tại sinh viên theo mã sinh viên
    boolean existsByStudentCode(String studentCode);

    // Kiểm tra tồn tại sinh viên theo email
    boolean existsByEmail(String email);

    // Tìm sinh viên theo điểm trung bình của một môn học cụ thể
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.course.id = :courseId AND g.averageScore >= :minScore")
    List<Student> findStudentsByCourseIdAndMinScore(@Param("courseId") Long courseId, @Param("minScore") Double minScore);

    // Tìm sinh viên chưa có điểm của một môn học
    @Query("select s from Student s Where s.id NOT IN (Select g.student.id From Grade g Where g.course.id = :courseId)")
    List<Student> findStudentsWithNoCourseGrade(@Param("courseId") Long courseId);

    // Tìm sinh viên theo học kỳ
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.semester = :semester")
    List<Student> findStudentsBySemester(@Param("semester") String semester);
}