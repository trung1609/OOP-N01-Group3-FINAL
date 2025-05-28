# Quản lý Sinh viên- N01 - Nhóm 3

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
