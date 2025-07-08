package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.service.CourseService;
import com.example.qlsv_oop_group3.service.GradeService;
import com.example.qlsv_oop_group3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;


    @GetMapping
    public String getAllGrades(Model model) {
        try {
            model.addAttribute("grades", gradeService.getAllGrades());
            model.addAttribute("grade", new Grade());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "grades";
        } finally {

        }
    }

    @PostMapping
    public String addGrade(@ModelAttribute Grade grade,
                           @RequestParam Long studentId,
                           @RequestParam Long courseId,
                           Model model) {
        try {
            gradeService.addGrade(grade, studentId, courseId);
            return "redirect:/grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("grades", gradeService.getAllGrades());
            model.addAttribute("grade", grade);
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "grades";
        } finally {

        }
    }

    @GetMapping("/edit/{id}")
    public String editGrade(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("grade", gradeService.getGradeById(id));
            model.addAttribute("grades", gradeService.getAllGrades());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "grades";
        } finally {

        }
    }

    @PostMapping("/update/{id}")
    public String updateGrade(@PathVariable Long id, @ModelAttribute Grade grade, Model model) {
        try {
            gradeService.updateGrade(id, grade);
            return "redirect:/grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("grades", gradeService.getAllGrades());
            model.addAttribute("grade", grade);
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            return "grades";
        } finally {

        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        try {
            gradeService.deleteGrade(id);
            return "redirect:/grades";
        } catch (Exception e) {
            return "redirect:/grades?error=" + e.getMessage();
        } finally {

        }
    }

    @GetMapping("/excellent")
    public String getExcellentStudents(Model model) {
        try {
            List<Map<String, Object>> students = gradeService.findExcellentStudents();
            model.addAttribute("excellentStudents", students);
            model.addAttribute("studentService", studentService);
            return "excellent_students";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "excellent_students";
        } finally {

        }
    }

    @GetMapping("/gradeA")
    public String getStudentsWithGradeA(@RequestParam(required = false) Long courseId, Model model) {
        try {
            if (courseId != null) {
                List<Map<String, Object>> students = gradeService.findStudentsWithGradeA(courseId);
                model.addAttribute("gradeAStudents", students);
                model.addAttribute("courseId", courseId);
            }
            model.addAttribute("courses", courseService.getAllCourse());
            return "students_grade_a";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "students_grade_a";
        } finally {

        }
    }

    @GetMapping("/by-faculty")
    public String getGradesByFaculty(@RequestParam String faculty, Model model) {
        try {
            model.addAttribute("grades", gradeService.findGradesByFaculty(faculty));
            model.addAttribute("grade", new Grade());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            model.addAttribute("facultySearched", faculty);
            return "grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "grades";
        } finally {

        }
    }

    @GetMapping("/by-course")
    public String getGradesByCourse(@RequestParam Long courseId, Model model) {
        try {
            model.addAttribute("grades", gradeService.getGradesByCourseId(courseId));
            model.addAttribute("grade", new Grade());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            model.addAttribute("courseSearched", courseId);
            return "grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "grades";
        } finally {

        }
    }

    @GetMapping("/by-semester")
    public String getGradesBySemester(@RequestParam String semester, Model model) {
        try {
            model.addAttribute("grades", gradeService.getGradesBySemester(semester));
            model.addAttribute("grade", new Grade());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("courses", courseService.getAllCourse());
            model.addAttribute("semesterSearched", semester);
            return "grades";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "grades";
        } finally {

        }
    }
}