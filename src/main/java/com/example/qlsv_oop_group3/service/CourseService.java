package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    // Repository thao tác với bảng Course
    private  final CourseRepo courseRepo;
    // Constructor inject repository
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    // (Không sử dụng) - method static trả về null
    public static Object getAllCourses() {
        return null;
    }

    // Tạo mới một khóa học, kiểm tra trùng mã môn học
    public Course createCourse(Course course){
        if(courseRepo.existsByCourseCode(course.getCourseCode())){
            throw new RuntimeException("Khóa học đã tồn tại");
        }
        return courseRepo.save(course);
    }
    // Lấy danh sách tất cả khóa học
    public List<Course> getAllCourse(){
        return courseRepo.findAll();
    }
    // Lấy thông tin khóa học theo id
    public Course getCourseById(Long id){
        return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với id: "+id));
    }
    // Cập nhật thông tin khóa học
    public Course updateCourse(Long id, Course course){
        Course existingCourse = getCourseById(id);
        if(!existingCourse.getCourseCode().equals(course.getCourseCode()) && courseRepo.existsByCourseCode(course.getCourseCode())){
            throw new RuntimeException("Khóa học đã tồn tại");
        }
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setCredit(course.getCredit());
        return courseRepo.save(existingCourse);
    }
    // Xóa khóa học theo id
    public void deleteCourseById(Long id){
        if(!courseRepo.existsById(id)){
            throw new RuntimeException("Không tìm thấy khóa học với id: "+id);
        }
        courseRepo.deleteById(id);
    }
    // Tìm khóa học theo mã môn học
    public Course findByCourseCode(String courseCode){
        return courseRepo.findByCourseCode(courseCode).orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với code: "+courseCode));
    }
    // Tìm kiếm khóa học theo tên (không phân biệt hoa thường)
    public List<Course> findByCourseName(String courseName){
        return courseRepo.findAllByCourseNameContainingIgnoreCase(courseName);
    }
    // Tìm kiếm khóa học theo số tín chỉ
    public List<Course> findByCredit(Integer credit){
        return courseRepo.findByCredit(credit);
    }
    // Tìm kiếm khóa học theo học kỳ
    public List<Course> findCourseBySemester(String semester){
        return courseRepo.findCourseBySemester(semester);
    }
}
