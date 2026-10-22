package com.example.materialx.data;

public class Course {
    private String code;
    private String name;
    private String department;
    private int year;
    private int semester;

    public Course() {
        // Required for Firebase
    }

    public Course(String code, String name, String department, int year, int semester) {
        this.code = code;
        this.name = name;
        this.department = department;
        this.year = year;
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
