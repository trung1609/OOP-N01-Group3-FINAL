package com.example.qlsv_oop_group3.repo;

import com.example.qlsv_oop_group3.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
    // Lấy danh sách điểm theo id sinh viên
    List<Grade> findByStudentId(Long studentId);

    // Lấy danh sách điểm theo id môn học
    List<Grade> findByCourseId(Long courseId);

    // Lấy danh sách điểm theo học kỳ
    List<Grade> findBySemester(String semester);


    // Tìm điểm của một sinh viên cho một môn học cụ thể
    @Query("Select g from Grade g where g.student.id = :studentId and g.course.id = :courseId")
    Grade findByStudentIdAndCourseId(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    // Lấy danh sách điểm của sinh viên theo học kỳ
    List<Grade> findByStudentIdAndSemester(Long studentId, String semester);

    // Tính GPA học kỳ của sinh viên
    @Query("SELECT SUM(g.score * g.course.credit) / SUM(g.course.credit) " +
            "FROM Grade g " +
            "WHERE g.student.id = :studentId AND g.semester = :semester")
    Double calculateSemesterGPA(@Param("studentId") Long studentId,
                                @Param("semester") String semester);
}