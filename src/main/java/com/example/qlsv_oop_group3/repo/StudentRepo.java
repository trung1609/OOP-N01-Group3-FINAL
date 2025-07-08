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
    //Tìm sv theo học kỳ
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.semester = :semester")
    List<Student> findStudentsBySemester(@Param("semester") String semester);
}
