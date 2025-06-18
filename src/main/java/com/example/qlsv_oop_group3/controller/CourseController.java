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
        try {
            model.addAttribute("courses", courseService.getAllCourse());
            model.addAttribute("course", new Course());
            return "courses";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "courses";
        } finally {

        }
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
        } finally {

        }
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("course", courseService.getCourseById(id));
            model.addAttribute("courses", courseService.getAllCourse());
            return "courses";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "courses";
        } finally {

        }
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        try {
            courseService.updateCourse(id, course);
            return "redirect:/courses";
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();
        } finally {

        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourseById(id);
            return "redirect:/courses";
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();
        } finally {

        }
    }

    @GetMapping("/code/{courseCode}")
    public String getCourseByCode(@PathVariable String courseCode, Model model) {
        try {
            model.addAttribute("courses", java.util.List.of(courseService.findByCourseCode(courseCode)));
            return "courses";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "courses";
        } finally {

        }
    }

    @GetMapping("/find-by-id")
    public String findCourseById(@RequestParam Long id, Model model) {
        try {
            Course foundCourse = courseService.getCourseById(id);
            model.addAttribute("foundCourse", foundCourse);
        } catch (Exception e) {
            model.addAttribute("courseError", e.getMessage());
        } finally {

        }
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("course", new Course());
        return "courses";
    }
}