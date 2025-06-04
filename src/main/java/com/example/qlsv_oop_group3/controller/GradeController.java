package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.service.CourseService;
import com.example.qlsv_oop_group3.service.GradeService;
import com.example.qlsv_oop_group3.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;

    public GradeController(GradeService gradeService, StudentService studentService, CourseService courseService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    //Them diem cho sinh vien
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> addGrade(
            @RequestBody Grade grade,
            @PathVariable Long studentId,//lấy giá trị từ URL path
            @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.addGrade(grade, studentId, courseId));
    }

    //Lay diem tu ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getGrade(@PathVariable Long id) {
        Grade grade = gradeService.getGradeById(id);
        return ResponseEntity.ok(new com.example.qlsv_oop_group3.dto.GradeDTO(grade));
    }

    //Lay tat ca diem
    @GetMapping
    public ResponseEntity<?> getAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        List<com.example.qlsv_oop_group3.dto.GradeDTO> gradeDTOs = new ArrayList<>();

        for (Grade grade : grades) {
            gradeDTOs.add(new com.example.qlsv_oop_group3.dto.GradeDTO(grade));
        }

        return ResponseEntity.ok(gradeDTOs);
    }

    //Lay diem tu 1 sinh vien
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentGrades(@PathVariable Long studentId) {
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);
        List<com.example.qlsv_oop_group3.dto.GradeDTO> gradeDTOs = new ArrayList<>();

        for (Grade grade : grades) {
            gradeDTOs.add(new com.example.qlsv_oop_group3.dto.GradeDTO(grade));
        }

        return ResponseEntity.ok(gradeDTOs);
    }

    //Lay diem tu 1 hoc phan cu the
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseGrades(@PathVariable Long courseId) {
        List<Grade> grades = gradeService.getGradesByCourseId(courseId);
        List<com.example.qlsv_oop_group3.dto.GradeDTO> gradeDTOs = new ArrayList<>();

        for (Grade grade : grades) {
            gradeDTOs.add(new com.example.qlsv_oop_group3.dto.GradeDTO(grade));
        }

        return ResponseEntity.ok(gradeDTOs);
    }

    //Xoa diem theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }

    //Cap nhat diem theo ID (REST API endpoint)
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGradeApi(
            @PathVariable Long id,//lấy giá trị từ URL path
            @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }


    // Lấy sinh viên có điểm A trong một khóa học cụ thể
    @GetMapping("/course/{courseId}/gradeA")
    public ResponseEntity<List<Map<String, Object>>> getStudentsWithGradeA(@PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.findStudentsWithGradeA(courseId));
    }

    // Lấy tất cả sinh viên có điểm xuất sắc
    @GetMapping("/excellent")
    public ResponseEntity<List<Map<String, Object>>> getExcellentStudents() {
        return ResponseEntity.ok(gradeService.findExcellentStudents());
    }
}