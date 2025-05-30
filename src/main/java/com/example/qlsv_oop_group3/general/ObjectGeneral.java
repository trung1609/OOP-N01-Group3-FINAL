
package com.example.qlsv_oop_group3.general;
//class cha ko thể tạo đối tượng từ class này
public abstract class ObjectGeneral {
    private int id;
    private String name;
    private String code;

    // Constructor
    public ObjectGeneral() {
    }

    public ObjectGeneral(int id, String name, String code) {
        try {
            this.id = id;
            this.name = name;
            this.code = code;
        } catch (Exception e) {
            throw new RuntimeException("Error creating ObjectGeneral: " + e.getMessage());
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        try {
            this.id = id;
        } catch (Exception e) {
            throw new RuntimeException("Error setting id: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            this.name = name;
        } catch (Exception e) {
            throw new RuntimeException("Error setting name: " + e.getMessage());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        try {
            this.code = code;
        } catch (Exception e) {
            throw new RuntimeException("Error setting code: " + e.getMessage());
        }
    }
}

// Class Student kế thừa ObjectGeneral
class Student extends ObjectGeneral {
    private String fullName;
    private String email;
    private String address;
    private String dateOfBirth;

    public Student() {
        super();
    }

    public Student(int id, String code, String name, String fullName, String email,
                   String address, String dateOfBirth) {
        super(id, name, code);
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

// Class Course kế thừa ObjectGeneral
class Course extends ObjectGeneral {
    private String courseName;
    private String courseCode;
    private Integer credit;

    public Course() {
        super();
    }

    public Course(int id, String code, String name, String courseName,
                  String courseCode, Integer credit) {
        super(id, name, code);
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

// Class Grade kế thừa ObjectGeneral
class Grade extends ObjectGeneral {
    private Double midtermScore;
    private Double finalScore;
    private Double score;
    private String semester;
    private Student student;
    private Course course;

    public Grade() {
        super();
    }

    public Grade(int id, String code, String name, Double midtermScore,
                 Double finalScore, Double score, String semester,
                 Student student, Course course) {
        super(id, name, code);
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.score = score;
        this.semester = semester;
        this.student = student;
        this.course = course;
    }

    // Getters and Setters
    public Double getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(Double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}