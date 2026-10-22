package com.example.materialx.data;

public class Material {
    public enum Type {
        PDF, PPT, DOC, ASSIGNMENT, PAST_PAPER
    }

    private String name;
    private Type type;
    private String uploadDate;
    private String courseCode;
    private String filePath;

    public Material() {
        // Required for Firebase
    }

    public Material(String name, Type type, String uploadDate, String courseCode, String filePath) {
        this.name = name;
        this.type = type;
        this.uploadDate = uploadDate;
        this.courseCode = courseCode;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
