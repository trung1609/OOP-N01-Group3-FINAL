package com.example.qlsv_oop_group3.controller;

import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    //Lay tat ca sinh vien
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    //Lay sinh vien theo id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    //Lay sinh vien da hoc trong 1 hoc ky nao do
    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<Student>> getStudentsBySemester(
            @PathVariable String semester) {
        return ResponseEntity.ok(studentService.findStudentsBySemester(semester));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

}
