package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.repo.CourseRepo;
import com.example.qlsv_oop_group3.repo.GradeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepo courseRepo;
    private final GradeRepo gradeRepo;
    public CourseService(CourseRepo courseRepo, GradeRepo gradeRepo) {
        this.courseRepo = courseRepo;
        this.gradeRepo = gradeRepo;
    }

    public Course createCourse(Course course){
        return courseRepo.save(course);
    }
    public List<Course> getAllCourse(){
        return courseRepo.findAll();
    }
    public Course getCourseById(Long id){
        return courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với id: "+id));
    }
    public Course updateCourse(Long id, Course course){
        Course existingCourse = getCourseById(id);
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setCredit(course.getCredit());
        return courseRepo.save(existingCourse);
    }
    public void deleteCourseById(Long id){
        if(!courseRepo.existsById(id)){
            throw new RuntimeException("Không tìm thấy khóa học với id: "+id);
        }
        // Xóa tất cả điểm liên quan đến khóa học này trước
        gradeRepo.deleteAll(gradeRepo.findByCourseId(id));
        courseRepo.deleteById(id);
    }
    public Course findByCourseCode(String courseCode){
        return courseRepo.findByCourseCode(courseCode).orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với code: "+courseCode));
    }
}