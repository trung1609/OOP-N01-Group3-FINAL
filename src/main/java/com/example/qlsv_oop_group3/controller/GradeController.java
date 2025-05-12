package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;
    //Them diem cho sinh vien
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> addGrade(
            @RequestBody Grade grade,
            @PathVariable Long studentId,
            @PathVariable Long courseId){
        return ResponseEntity.ok(gradeService.addGrade(grade, studentId, courseId));
    }

    //Cap nhat diem theo ID
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable Long id,
            @RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }

    //Lay diem tu ID
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long id){
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }

    //Lay tat ca diem
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades(){
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    //Lay diem tu 1 sinh vien
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId){
        return ResponseEntity.ok(gradeService.getGradesByStudentId(studentId));
    }

    //Lay diem tu 1 hoc phan cu the
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId){
        return ResponseEntity.ok(gradeService.getGradesByCourseId(courseId));
    }

    //Lay bang diem theo hoc ky
    @GetMapping("/academic-record/semester")
    public ResponseEntity<Map<String, Object>> getSemesterRecord(
            @RequestParam Long studentId,
            @RequestParam String semester){
        return ResponseEntity.ok(gradeService.getSemesterAcademicRecord(studentId, semester));
    }

    //Lay diem trung binh tich luy CGPA
    @GetMapping("/academic-record/cumulative")
    public ResponseEntity<Map<String, Object>> getCumulativeRecord(@RequestParam Long studentId){
        return ResponseEntity.ok(gradeService.calculateCumulativeGPA(studentId));
    }

    //Xoa diem theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id){
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }
}
