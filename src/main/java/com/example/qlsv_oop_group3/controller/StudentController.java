package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.createStudent(student);
            return "redirect:/students";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("students", studentService.getAllStudent());
            model.addAttribute("student", student);
            return "students";
        }
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("students", studentService.getAllStudent());
        return "students";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/semester/{semester}")
    public String getStudentsBySemester(@PathVariable String semester, Model model) {
        model.addAttribute("students", studentService.findStudentsBySemester(semester));
        return "students";
    }

    @GetMapping("/faculty")
    public String getStudentsByFaculty(@RequestParam String faculty, Model model) {
        model.addAttribute("students", studentService.findStudentsByFaculty(faculty));
        model.addAttribute("student", new Student());
        model.addAttribute("facultySearched", faculty);
        return "students";
    }
}

@Controller
class RootController {
    @GetMapping("/")
    public String redirectToStudents() {
        return "redirect:/students";
    }
}