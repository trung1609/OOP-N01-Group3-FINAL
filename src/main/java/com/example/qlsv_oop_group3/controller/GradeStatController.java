package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grade-stats")
public class GradeStatController {

    private final GradeService gradeService;

    @Autowired
    public GradeStatController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/students-with-grade-a/{courseId}")
    public ResponseEntity<List<Map<String, Object>>> getStudentsWithGradeA(@PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(gradeService.findStudentsWithGradeA(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/excellent-students")
    public ResponseEntity<List<Map<String, Object>>> getExcellentStudents() {
        try {
            return ResponseEntity.ok(gradeService.findExcellentStudents());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
