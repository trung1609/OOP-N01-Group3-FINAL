# Quản lý Sinh viên và điểm - N01 - Nhóm 3

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
- Ứng dụng được xây dựng với giao diện Java Spring Boot version 3.4.5
- Java 17
- JDK 22
- MySql
- Sử dụng database trên Aiven

## Chức năng
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

## UML Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/20e79c7d-373e-43f9-943a-6a74d715b869)

# UML Sequence Diagram Quan Ly Khoa Hoc
![UML sequence diagram](https://github.com/user-attachments/assets/0be6d0fe-af86-4ed2-af91-6256d02f2be6)

# UML Sequence Diagram Quan Ly Sinh Vien
![UML sequence diagram QLSV](https://github.com/user-attachments/assets/ff34fe07-0a2f-4cab-86e4-6b11972a15a4)

# UML Sequence Diagram Quan Ly Diem
![UML sequence diagram QLD](https://github.com/user-attachments/assets/9a733d57-72b4-4cf8-8000-8b18cb53d477)




## Lưu Đồ Thuật Toán: 

![Luu Do Thuat Toan ](https://github.com/user-attachments/assets/5eb9f951-83f8-40c4-ae06-2b0b2e844ba2)





### Giao diện grades:

![Screenshot 2025-06-18 163047](https://github.com/user-attachments/assets/fc951912-28e8-405f-8158-387df95dc2c0)


### Giao diện Sinh viên xuất sắc:
![Screenshot 2025-06-18 163113](https://github.com/user-attachments/assets/dffe79ac-da18-4c91-8250-956c42efc9ac)

### Giao diện Sinh viên đạt điểm A:
![Screenshot 2025-06-18 163151](https://github.com/user-attachments/assets/d6c52e72-490a-48ad-a728-4c47de8d93b7)

### Giao diện students:
![Ảnh1](https://github.com/user-attachments/assets/6fdd12fc-d1f0-4e5d-9d0f-756bc663df73)

### Giao diện courses:
![Ảnh2](https://github.com/user-attachments/assets/8f4f8232-5827-4f6e-abee-acc170b558c6)

### Github pages dự án
 https://github.com/trung1609/OOP-N01-Group3-FINAL.git

### Cách Cài Đặt
Clone repository từ GitHub:
git clone https://github.com/trung1609/OOP-N01-Group3-FINAL.git






