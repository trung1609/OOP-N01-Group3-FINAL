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
import java.util.stream.Collectors;

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
        if (studentId == null) {
            throw new RuntimeException("ID sinh viên không được để trống");
        }
        if (courseId == null) {
            throw new RuntimeException("ID khóa học không được để trống");
        }
        if (grade.getMidtermScore() == null || grade.getFinalScore() == null) {
            throw new RuntimeException("Điểm giữa kỳ và cuối kỳ không được để trống");
        }

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));

//        //Kiem tra diem da ton tai chua
//        if(gradeRepo.findByStudentIdAndCourseId(studentId, courseId)!=null){
//            throw new RuntimeException("Điểm của sinh viên cho môn học này đã tồn tại");
//        }
        validateScores(grade.getMidtermScore(), grade.getFinalScore());
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setScore(calculateCourseScore(grade.getMidtermScore(), grade.getFinalScore()));

        return gradeRepo.save(grade);
    }
    //Cap nhat diem
    public Grade updateGrade(Long id, Grade gradeUpdate) {
        Grade existingGrade = gradeRepo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy điểm"));
        if (gradeUpdate.getMidtermScore() == null || gradeUpdate.getFinalScore() == null) {
            throw new RuntimeException("Điểm giữa kỳ và cuối kỳ không được để trống");
        }
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
            throw new RuntimeException("Điểm phải nằm trong khoảng 0-10");
        }
        if (finalScore != null && (finalScore < 0 || finalScore > 10)) {
            throw new RuntimeException("Điểm phải nằm trong khoảng 0-10");
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

            Map<String, Object> courseResult = new java.util.HashMap<>();
            courseResult.put("courseCode", course.getCourseCode());
            courseResult.put("courseName", course.getCourseName());
            courseResult.put("credits", credits);
            courseResult.put("midtermScore", grade.getMidtermScore());
            courseResult.put("finalScore", grade.getFinalScore());
            courseResult.put("score", score);
            courseResult.put("letterGrade", letterGrade);
            courseResult.put("gradePoint", gradePoint);
            courseResults.add(courseResult);
        }

        //Tinh GPA hoc ky
        Double semesterGPA = totalCredits > 0 ? Math.round((totalGradePoints / totalCredits) * 100.0) /100.0 : 0.0;

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("studentId", studentId);
        result.put("studentName", student.getFullName());
        result.put("studentCode", student.getStudentCode());
        result.put("semester", semester);
        result.put("courses", courseResults);
        result.put("totalCredits", totalCredits);
        result.put("semesterGPA", semesterGPA);

        return result;
    }

    //Tính điểm trung bình tích lũy (CPA)
    public Map<String, Object>calculateCumulativeGPA(Long studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        List<Grade> allGrades = gradeRepo.findByStudentId(studentId);
        int totalCredits = 0;
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

        Map<String, Object> result = new java.util.HashMap<>();
        result.put("studentId", studentId);
        result.put("studentName", student.getFullName());
        result.put("studentCode", student.getStudentCode());
        result.put("totalCredits", totalCredits);
        result.put("cpa", cpa);
        result.put("academicStatus", getAcademicStatus(cpa));

        return result;
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

    //Tìm sinh viên đạt điểm A cho một môn học cụ thể
    public List<Map<String, Object>> findStudentsWithGradeA(Long courseId) {
        List<Grade> courseGrades = gradeRepo.findByCourseId(courseId);
        List<Map<String, Object>> studentsWithGradeA = courseGrades.stream()
                .filter(grade -> grade.getScore() != null && grade.getScore() >= 8.5)
                .map(grade -> {
                    Student student = grade.getStudent();
                    Map<String, Object> studentData = new java.util.HashMap<>();
                    studentData.put("studentId", student.getId());
                    studentData.put("studentCode", student.getStudentCode());
                    studentData.put("studentName", student.getFullName());
                    studentData.put("score", grade.getScore());
                    studentData.put("letterGrade", "A");
                    return studentData;
                })
                .collect(Collectors.toList());
        return studentsWithGradeA;
    }

    //Tìm sinh viên có học lực xuất sắc trong toàn trường
    public List<Map<String, Object>> findExcellentStudents() {
        return studentRepo.findAll().stream()
                .map(student -> {
                    try {
                        Map<String, Object> cumulativeRecord = calculateCumulativeGPA(student.getId());
                        Double cpa = (Double) cumulativeRecord.get("cpa");
                        if (cpa != null && cpa >= 3.6) {
                            Map<String, Object> studentData = new java.util.HashMap<>();
                            studentData.put("studentId", student.getId());
                            studentData.put("studentCode", student.getStudentCode());
                            studentData.put("studentName", student.getFullName());
                            studentData.put("email", student.getEmail());
                            studentData.put("address", student.getAddress());
                            studentData.put("dateOfBirth", student.getDateOfBirth());
                            studentData.put("cpa", cpa);
                            studentData.put("totalCredits", cumulativeRecord.get("totalCredits"));
                            studentData.put("academicStatus", "Xuất sắc");
                            return studentData;
                        }
                    } catch (Exception e) {
                        System.out.println("Không thể tính CPA cho sinh viên ID=" + student.getId() + ": " + e.getMessage());
                    }
                    return null;
                })
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Tìm điểm của sinh viên theo khoa
    public List<Grade> findGradesByFaculty(String faculty) {
        return gradeRepo.findAll().stream()
                .filter(g -> g.getStudent() != null && faculty != null
                        && faculty.equalsIgnoreCase(g.getStudent().getFaculty()))
                .toList();
    }

    public List<Grade> getGradesBySemester(String semester) {
        return gradeRepo.findBySemester(semester);
    }
    
}