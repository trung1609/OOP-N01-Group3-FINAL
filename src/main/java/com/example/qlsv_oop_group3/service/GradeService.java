package com.example.qlsv_oop_group3.service;

import com.example.qlsv_oop_group3.entity.Course;
import com.example.qlsv_oop_group3.entity.Grade;
import com.example.qlsv_oop_group3.entity.Student;
import com.example.qlsv_oop_group3.repo.CourseRepo;
import com.example.qlsv_oop_group3.repo.GradeRepo;
import com.example.qlsv_oop_group3.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GradeService {
    private final GradeRepo gradeRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    public GradeService(GradeRepo gradeRepo, CourseRepo courseRepo, StudentRepo studentRepo) {
        this.gradeRepo = gradeRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    //Them diem moi cho sinh vien
    public Grade addGrade(Grade grade, Long studentId, Long courseId){
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));

        //Kiem tra diem da ton tai chua
        if(gradeRepo.findByStudentIdAndCourseId(studentId, courseId)!=null){
            throw new RuntimeException("Điểm của sinh viên cho môn học này đã tồn tại");
        }
        validateScores(grade.getMidtermScore(), grade.getFinalScore());
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setScore(calculateCourseScore(grade.getMidtermScore(), grade.getFinalScore()));

        return gradeRepo.save(grade);
    }
    //Cap nhat diem
    public Grade updateGrade(Long id, Grade gradeUpdate) {
        Grade existingGrade = gradeRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy điểm"));
        validateScores(gradeUpdate.getMidtermScore(), gradeUpdate.getFinalScore());

        existingGrade.setMidtermScore(gradeUpdate.getMidtermScore());
        existingGrade.setFinalScore(gradeUpdate.getFinalScore());
        existingGrade.setSemester(gradeUpdate.getSemester());
        existingGrade.setScore(calculateCourseScore(gradeUpdate.getMidtermScore(), gradeUpdate.getFinalScore()));
        return gradeRepo.save(existingGrade);
    }

    //Kiem tra diem so
    private void validateScores(Double midtermScore, Double finalScore) {
        if (midtermScore != null && (midtermScore < 0 || midtermScore > 10)) {
            throw new RuntimeException("Điểm giữa kỳ phải nằm trong khoảng 0-10");
        }
        if (finalScore != null && (finalScore < 0 || finalScore > 10)) {
            throw new RuntimeException("Điểm cuối kỳ phải nằm trong khoảng 0-10");
        }
    }

    //Tinh diem tong ket mon hoc thang diem 10
    private Double calculateCourseScore(Double midtermScore, Double finalScore) {
        if (midtermScore == null || finalScore == null) {
            return null;
        }
        // Tính điểm theo trọng số: 30% giữa kỳ + 70% cuối kỳ
        double score = (midtermScore * 0.3) + (finalScore * 0.7);
        // Làm tròn đến 1 chữ số thập phân
        return Math.round(score * 10.0) / 10.0;
    }

    //Chuyen doi diem so sang diem chu
    public String convertScoreToLetter(Double score) {
        if (score == null) return "N/A";
        if (score >= 8.5) return "A";
        if (score >= 8.0) return "B+";
        if (score >= 7.0) return "B";
        if (score >= 6.5) return "C+";
        if (score >= 5.5) return "C";
        if (score >= 5.0) return "D+";
        if (score >= 4.0) return "D";
        return "F";
    }
    public Double convertToGradePoint(String letterGrade) {
        return switch (letterGrade) {
            case "A" -> 4.0;
            case "B+" -> 3.5;
            case "B" -> 3.0;
            case "C+" -> 2.5;
            case "C" -> 2.0;
            case "D+" -> 1.5;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }
    //Lấy kết quả học tập chi tiết của sinh viên trong học kỳ
    public Map<String, Object> getSemesterAcademicRecord(Long studentId, String semester){
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        List<Grade> semesterGrade = gradeRepo.findByStudentIdAndSemester(studentId, semester);

        Double totalCredits = 0.0;
        Double totalGradePoints = 0.0;
        List<Map<String, Object>> courseResults = new ArrayList<>();

        for(Grade grade : semesterGrade){
            Course course = grade.getCourse();
            Integer credits = course.getCredit();
            Double score = grade.getScore();
            String letterGrade = convertScoreToLetter(score);
            Double gradePoint = convertToGradePoint(letterGrade);
            totalCredits += credits;
            totalGradePoints += credits * gradePoint;

            courseResults.add(Map.of(
            "courseCode", course.getCourseCode(),
            "courseName", course.getCourseName(),
            "credits", credits,
            "midtermScore", grade.getMidtermScore(),
            "finalScore", grade.getFinalScore(),
            "score", score,
            "letterGrade", letterGrade,
            "gradePoint", gradePoint
            ));
        }

        //Tinh GPA hoc ky
        Double semesterGPA = totalCredits > 0 ? Math.round((totalGradePoints / totalCredits) * 100.0) /100.0 : 0.0;

        return Map.of(
                "studentId", studentId,
                "studentName", student.getFullName(),
                "studentCode", student.getStudentCode(),
                "semester", semester,
                "courses", courseResults,
                "totalCredits", totalCredits,
                "semesterGPA", semesterGPA
        );
    }

    //Tính điểm trung bình tích lũy (CPA)
    public Map<String, Object>calculateCumulativeGPA(Long studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        List<Grade> allGrades = gradeRepo.findByStudentId(studentId);
        Double totalCredits = 0.0;
        Double totalGradePoints = 0.0;
        for(Grade grade : allGrades){
            if(grade.getScore() != null){
                Integer credits = grade.getCourse().getCredit();
                String letterGrade = convertScoreToLetter(grade.getScore());
                Double gradePoint = convertToGradePoint(letterGrade);

                totalCredits += credits;
                totalGradePoints += (gradePoint * credits);
            }
        }

        //Tinh CPA
        Double cpa = totalCredits > 0 ? Math.round((totalGradePoints / totalCredits) * 100.0) /100.0 : 0.0;
        return Map.of(
                "studentId", studentId,
                "studentName", student.getFullName(),
                "studentCode", student.getStudentCode(),
                "totalCredits", totalCredits,
                "cpa", cpa,
                "academicStatus", getAcademicStatus(cpa)
        );
    }
    //Xac dinh xep loai hoc luc
    private String getAcademicStatus(double gpa) {
        if (gpa >= 3.6) return "Xuất sắc";
        if (gpa >= 3.2) return "Giỏi";
        if (gpa >= 2.5) return "Khá";
        if (gpa >= 2.0) return "Trung bình";
        return "Yếu";
    }
    //Tìm điểm theo id
    public Grade getGradeById(Long id){
        return gradeRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy điểm"));
    }
    //Trả về danh sách tất cả điểm trong hệ thống
    public List<Grade> getAllGrades(){
        return gradeRepo.findAll();
    }
    //Lấy danh sách điểm của một sinh viên cụ thể
    public List<Grade> getGradesByStudentId(Long studentId){
        return gradeRepo.findByStudentId(studentId);
    }
    //Lấy danh sách điểm của một học phần cụ thể
    public List<Grade> getGradesByCourseId(Long courseId){
        return gradeRepo.findByCourseId(courseId);
    }
    //Xóa 1 đểm theo id
    public void deleteGrade(Long id){
        if(!gradeRepo.existsById(id)){
            throw new RuntimeException("Không tìm thấy điểm");
        }
        gradeRepo.deleteById(id);
    }

    public void saveGrade(Grade grade) {
    }
}
