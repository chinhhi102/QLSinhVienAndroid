package com.example.quanlysv;

public class Student {
    private String tenSV;
    private String maSV;

    public Student() {
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    private String lop;
    private String age;
    private String Email;
    private String Phone;
    private String diaChi;

    public Student(String tenSV, String maSV, String lop, String age, String email, String phone, String diaChi) {
        this.tenSV = tenSV;
        this.maSV = maSV;
        this.lop = lop;
        this.age = age;
        Email = email;
        Phone = phone;
        this.diaChi = diaChi;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return getTenSV() + "_" + getMaSV() + "_" + getLop() + "_" + getAge() + "_" + getEmail() + "_" + getPhone() + "_" + getDiaChi();
    }
}