# Quản lý Sinh viên- N01 - Nhóm 3

# Sinh Viên Thực Hiện:
### - Vũ Minh Trung MSV: 23010361
### - Nguyễn Tường Hưng MSV: 23010439

## Mô tả dự án
Hệ thống quản lý sinh viên với các chức năng cơ bản về quản lý thông tin sinh viên, môn học và điểm số.

## Cấu trúc đối tượng
1. Student: Quản lý thông tin sinh viên
   - Thông tin cá nhân
   - Mã sinh viên
   - Điểm số

2. Course: Quản lý thông tin môn học
   - Tên môn học
   - Mã môn học
   - Số tín chỉ

3. Grade: Quản lý điểm số
   - Liên kết sinh viên và môn học
   - Điểm số
   - Học kỳ

## Cấu trúc thư mục
- src/main/java: Mã nguồn chính
- src/main/resources: Tài nguyên
- src/test/java: Mã nguồn kiểm thử

## Kiểm thử
Sử dụng JUnit và Spring Boot Test để kiểm thử các chức năng:
- Tạo mới sinh viên
- Cập nhật thông tin

## Giao diện
- Ứng dụng được xây dựng với giao diện Java Spring Boot

## Quản Lý
1. Student: Quản lý thông tin sinh viên
- Thêm mới, chỉnh sửa, xóa thông tin sinh viên
- Liệt kê danh sách sinh viên 

2. Course: Quản lí thông tin môn học
- Tạo mới, chỉnh sửa, xóa môn học
- Lưu điểm các môn học của sinh viên

3. Grade: Quản lí điểm số
- Lưu, thêm, chỉnh sửa điểm số

## Dữ Liệu
- Dữ liệu được lưu dưới dạng file nhị phân
- Trong quá trình làm việc , dữ liệu được lưu tạm bằng các Collection: ArrayList<SinhVien>, HashMap<String, LopHoc>, LinkedList<MonHoc>


## Class Diagram


Link repo: https://github.com/trung1609/OOP-N01-Group3-FINAL.git 

  UMLL
UML Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/20e79c7d-373e-43f9-943a-6a74d715b869)

UML Sequence Diagram Quan Ly Khoa Hoc
![UML sequence diagram](https://github.com/user-attachments/assets/0be6d0fe-af86-4ed2-af91-6256d02f2be6)

UML Sequence Diagram Quan Ly Sinh Vien
![UML sequence diagram QLSV](https://github.com/user-attachments/assets/ff34fe07-0a2f-4cab-86e4-6b11972a15a4)

UML Sequence Diagram Quan Ly Diem
![UML sequence diagram QLD](https://github.com/user-attachments/assets/9a733d57-72b4-4cf8-8000-8b18cb53d477)

## Câu 2:

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

### Miêu tả phương thức:

Phương thức findExcellentStudents() dùng để lấy danh sách các sinh viên có học lực xuất sắc ("Xuất sắc") trong toàn trường. Phương thức này tính điểm trung bình tích lũy (CPA) cho từng sinh viên và chọn ra những sinh viên có CPA lớn hơn hoặc bằng 3.6. Kết quả trả về là danh sách các bản ghi chứa thông tin sinh viên và học lực của sinh viên đó.

Kết quả chạy API:

![Screenshot 2025-06-06 140232](https://github.com/user-attachments/assets/38529047-f44a-4c3f-ab8a-c5f421103525)









  Link repo: https://github.com/trung1609/OOP-N01-Group3-FINAL.git


