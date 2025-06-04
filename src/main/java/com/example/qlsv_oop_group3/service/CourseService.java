package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private  final CourseRepo courseRepo;
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course createCourse(Course course){
        if(courseRepo.existsByCourseCode(course.getCourseCode())){
            throw new RuntimeException("Khóa học đã tồn tại");
        }
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
        if(!existingCourse.getCourseCode().equals(course.getCourseCode()) && courseRepo.existsByCourseCode(course.getCourseCode())){
            throw new RuntimeException("Khóa học đã tồn tại");
        }
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setCredit(course.getCredit());
        return courseRepo.save(existingCourse);
    }
    public void deleteCourseById(Long id){
        if(!courseRepo.existsById(id)){
            throw new RuntimeException("Không tìm thấy khóa học với id: "+id);
        }
        courseRepo.deleteById(id);
    }
    public Course findByCourseCode(String courseCode){
        return courseRepo.findByCourseCode(courseCode).orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với code: "+courseCode));
    }
}
