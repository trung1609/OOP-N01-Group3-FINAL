package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }
    //Lay toan bo khoa hoc
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }
    //Lay khoa hoc theo id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course){
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok().build();
    }
    //Tìm môn học theo mã môn học
    @GetMapping("/code/{courseCode}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String courseCode){
        return ResponseEntity.ok(courseService.findByCourseCode(courseCode));
    }

}
