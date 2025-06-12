package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("course", new Course());
        return "courses";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course, Model model) {
        try {
            courseService.createCourse(course);
            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("courses", courseService.getAllCourse());
            model.addAttribute("course", course);
            return "courses";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("courses", courseService.getAllCourse());
        return "courses";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/code/{courseCode}")
    public String getCourseByCode(@PathVariable String courseCode, Model model) {
        model.addAttribute("courses", java.util.List.of(courseService.findByCourseCode(courseCode)));
        return "courses";
    }
}
