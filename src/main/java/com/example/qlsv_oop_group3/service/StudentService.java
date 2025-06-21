package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.repo.GradeRepo;
import com.example.qlsv_oop_group3.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final GradeRepo gradeRepo;
    public StudentService(StudentRepo studentRepo, GradeRepo gradeRepo) {
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
    }
    public Student createStudent(Student student){
        validateStudentCode(student.getStudentCode());
        validateEmail(student.getEmail());
        validateFullName(student.getFullName());
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
        validateFullName(student.getFullName());
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
//    public void checkDuplicateStudent(Student student) {
//        if (studentRepo.existsByStudentCode(student.getStudentCode())) {
//            throw new RuntimeException("Mã sinh viên đã tồn tại");
//        }
//        if (studentRepo.existsByEmail(student.getEmail())) {
//            throw new RuntimeException("Email đã tồn tại");
//        }
//    }
    // Kiểm tra định dạng mã sinh viên: chỉ số, 8 ký tự số
    private void validateStudentCode(String studentCode) {
        if (studentCode == null || !studentCode.matches("^\\d{8}$")) {
            throw new RuntimeException("Mã sinh viên phải gồm 8 số.");
        }
    }

    // Kiểm tra định dạng email: phải đúng @gmail.com hoặc @st.phenikaa-uni.edu.vn
    private void validateEmail(String email) {
        if (email == null ||
            !(Pattern.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$", email) ||
              Pattern.matches("^[A-Za-z0-9+_.-]+@st\\.phenikaa-uni\\.edu\\.vn$", email))) {
            throw new RuntimeException("Email chưa đúng định dạng.");
        }
    }

    // Kiểm tra tên chỉ chứa chữ cái và khoảng trắng
    private void validateFullName(String fullName) {
        if (fullName == null || !fullName.matches("^[\\p{L} ]+$")) {
            throw new RuntimeException("Họ tên chỉ được chứa chữ cái và khoảng trắng.");
        }
    }

    // Tìm sinh viên theo khoa
    public List<Student> findStudentsByFaculty(String faculty) {
        return studentRepo.findAll().stream()
                .filter(s -> s.getFaculty() != null && s.getFaculty().equalsIgnoreCase(faculty))
                .toList();
    }
}