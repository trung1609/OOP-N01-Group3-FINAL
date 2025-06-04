package com.example.qlsv_oop_group3.repo;

import com.example.qlsv_oop_group3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    //Tìm theo mã môn học
    Optional<Course> findByCourseCode(String courseCode);
    boolean existsByCourseCode(String courseCode);
    List<Course> findByCredit(Integer credit);
    //Tìm khóa học theo học kỳ
    @Query("Select distinct c from Course c Join c.grades g where g.semester = :semester")
    List<Course> findCourseBySemester(@Param("semester") String semester);
    //Lấy danh sách các môn học mà một sinh viên cụ thể đã học (có điểm)
    @Query("SELECT DISTINCT c FROM Course c JOIN c.grades g WHERE g.student.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
    //Tìm khóa học có số tín chỉ lớn hơn hoặc bằng giá trị cụ thể
    @Query("SELECT c FROM Course c WHERE c.credit >= :minCredit")
    List<Course> findCoursesByMinimumCredit(@Param("minCredit") Integer minCredit);
}
