package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.service.CourseService;
import com.example.qlsv_oop_group3.service.GradeService;
import com.example.qlsv_oop_group3.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.addGrade(grade, studentId, courseId));
    }

    //Lay diem tu ID
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }

    //Lay tat ca diem
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    //Lay diem tu 1 sinh vien
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudentId(studentId));
    }

    //Lay diem tu 1 hoc phan cu the
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getGradesByCourseId(courseId));
    }

    //Lay bang diem theo hoc ky
    @GetMapping("/academic-record/semester")
    public ResponseEntity<Map<String, Object>> getSemesterRecord(
            @RequestParam Long studentId,
            @RequestParam String semester) {
        return ResponseEntity.ok(gradeService.getSemesterAcademicRecord(studentId, semester));
    }

    //Lay diem trung binh tich luy CGPA
    @GetMapping("/academic-record/cumulative")
    public ResponseEntity<Map<String, Object>> getCumulativeRecord(@RequestParam Long studentId) {
        return ResponseEntity.ok(gradeService.calculateCumulativeGPA(studentId));
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
            @PathVariable Long id,
            @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }

<<<<<<< HEAD
=======
    // MVC Controller methods
    @GetMapping("/view")
    public String listGrades(Model model) {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "grade/list";
    }

    @GetMapping("/view/add")
    public String addGradeForm(Model model) {
        model.addAttribute("grade", new Grade());
        return "grade/add";
    }

    @PostMapping("/view/add")
    public String submitGrade(@ModelAttribute Grade grade) {
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/view/edit/{id}")
    public String editGradeForm(@PathVariable Long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade", grade);
        return "grade/edit";
    }

    //Cap nhat diem theo ID (MVC endpoint)
    @PostMapping("/view/edit/{id}")
    public String updateGradeMvc(@PathVariable Long id, @ModelAttribute Grade grade) {
        grade.setId(id);
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/view/delete/{id}")
    public String deleteGradeMvc(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grades";
    }
>>>>>>> 75a43c4642799cb26b42214365ba6ddb4aca855a
}