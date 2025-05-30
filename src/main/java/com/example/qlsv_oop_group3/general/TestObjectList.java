
package com.example.qlsv_oop_group3.general;

public class TestObjectList {
    public static void main(String[] args) {
        try {
            // Tạo các đối tượng quản lý
            ObjectList<Student> studentList = new ObjectList<>();
            ObjectList<Course> courseList = new ObjectList<>();
            ObjectList<Grade> gradeList = new ObjectList<>();

            // Test Student CRUD
            System.out.println("===== Testing Student CRUD =====");

            // Create
            Student student1 = new Student(1, "SV001", "Student 1", "Nguyen Van A",
                    "nva@example.com", "Ha Noi", "2000-01-01");
            Student student2 = new Student(2, "SV002", "Student 2", "Tran Thi B",
                    "ttb@example.com", "Ho Chi Minh", "2000-02-02");

            studentList.create(student1);
            studentList.create(student2);
            System.out.println("Created students successfully!");

            for (Student s : studentList.getAll()) {
                System.out.println("ID: " + s.getId() + ", Tên: " + s.getFullName());
            }


            // Read
            Student retrievedStudent = studentList.read(2);
            System.out.println("Họ tên sinh viên: " + retrievedStudent.getFullName());

            // Update
            student1.setEmail("nva.updated@example.com");
            studentList.update(student1);
            System.out.println("Updated student email: " + studentList.read(1).getEmail());

            // Delete
            try {
                studentList.delete(2);
                System.out.println("Đã xóa sinh viên thành công");
            } catch (RuntimeException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
            System.out.println("Tổng số sinh viên: " + studentList.getAll().size());


            // Test Course CRUD
            System.out.println("\n===== Testing Course CRUD =====");

            // Create
            Course course1 = new Course(1, "CS001", "Course 1", "Java Programming",
                    "JAVA001", 3);
            Course course2 = new Course(2, "CS002", "Course 2", "Python Programming",
                    "PY001", 3);

            courseList.create(course1);
            courseList.create(course2);
            System.out.println("Created courses successfully!");

            // Read
            Course retrievedCourse = courseList.read(1);
            System.out.println("Tên Khóa Học: " + retrievedCourse.getCourseName());

            // Update
            course1.setCredit(2);
            courseList.update(course1);
            System.out.println("Updated course credit: " + courseList.read(1).getCredit());

            // Delete
            courseList.delete(2);
            System.out.println("Deleted course successfully");
            System.out.println("Total courses: " + courseList.getAll().size());

            // Test Grade CRUD
            System.out.println("\n===== Testing Grade CRUD =====");

            // Create
            Grade grade1 = new Grade(1, "G001", "Grade 1", 8.0, 9.0, 8.5,
                    "Spring 2024", student1, course1);

            gradeList.create(grade1);
            System.out.println("Created grade successfully!");

            // Read
            Grade retrievedGrade = gradeList.read(1);
            System.out.println("Tên điểm: Student=" +
                    retrievedGrade.getStudent().getFullName() +
                    ", Course=" + retrievedGrade.getCourse().getCourseName() +
                    ", Final Score=" + retrievedGrade.getFinalScore());

            // Update
            grade1.setFinalScore(9.5);
            gradeList.update(grade1);
            System.out.println("Updated grade final score: " + gradeList.read(1).getFinalScore());

            // Test error handling
            System.out.println("\n===== Testing Error Handling =====");
            try {
                studentList.read(999); // Should throw exception
            } catch (RuntimeException e) {
                System.out.println("Expected error caught: " + e.getMessage());
            }

            System.out.println("\nAll tests completed successfully!");

        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}