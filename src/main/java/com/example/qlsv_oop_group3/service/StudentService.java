package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.repo.GradeRepo;
import com.example.qlsv_oop_group3.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final GradeRepo gradeRepo;
    public StudentService(StudentRepo studentRepo, GradeRepo gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
    }
    public Student createStudent(Student student){
//        checkDuplicateStudent(student);
        return studentRepo.save(student);
    }
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
    }
    public Student updateStudent(Long id, Student student){
        Student existingStudent = getStudentById(id);
        existingStudent.setFullName(student.getFullName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setStudentCode(student.getStudentCode());
        return studentRepo.save(existingStudent);
    }
    //Lấy danh sách tất cả sinh viên đã có môn học/điểm trong học kỳ cụ thể
    public List<Student> findStudentsBySemester(String semester) {
        return studentRepo.findStudentsBySemester(semester);
    }
    public void deleteStudentById(Long id){
        // Xóa tất cả điểm liên quan đến sinh viên này trước
        gradeRepo.deleteAll(gradeRepo.findByStudentId(id));
        studentRepo.deleteById(id);
    }
    public void checkDuplicateStudent(Student student) {
        if (studentRepo.existsByStudentCode(student.getStudentCode())) {
            throw new RuntimeException("Mã sinh viên đã tồn tại");
        }
        if (studentRepo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
    }
}