package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.repo.GradeRepo;
import com.example.qlsv_oop_group3.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    // Repository thao tác với bảng Student
    private final StudentRepo studentRepo;
    // Repository thao tác với bảng Grade (dùng cho các nghiệp vụ liên quan đến điểm)
    private final GradeRepo gradeRepo;

    // Constructor inject các repository
    public StudentService(StudentRepo studentRepo, GradeRepo gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
    }

    // Tạo mới một sinh viên
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    // Lấy danh sách tất cả sinh viên
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    // Tìm sinh viên theo tên (không phân biệt hoa thường)
    public List<Student> findStudentsByName(String name) {
        return studentRepo.findAllByFullNameContainingIgnoreCase(name);
    }

    // Lấy thông tin sinh viên theo id, nếu không có sẽ ném ra exception
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
    }

    // Cập nhật thông tin sinh viên theo id
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = getStudentById(id);
        existingStudent.setFullName(student.getFullName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setStudentCode(student.getStudentCode());
        return studentRepo.save(existingStudent);
    }

    // Lấy danh sách sinh viên đã học trong một học kỳ cụ thể
    public List<Student> findStudentsBySemester(String semester) {
        return studentRepo.findStudentsBySemester(semester);
    }

    // Xóa sinh viên theo id
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }
}
