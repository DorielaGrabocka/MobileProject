package com.example.models;

public class Comment {
    private String comment;
    private int studentId;
    private int courseId;

    public Comment(String comment, int studentId, int courseId) {
        this.comment = comment;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}
