package com.example.models;

import java.io.Serializable;

public class StudentCourse implements Serializable {

    private Course course;

    //get from database
    private String grade;
    private int status;//done(1) or currently taking(0) or bookmarked(2)
    private int  studentId;
    private int courseId;

    public StudentCourse() {
    }

    public StudentCourse(String grade, int status, int studentId, int courseId) {
        this.grade = grade;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    /**Method to get the quality points from letter grade
     * @return the corresponding quality points*/
    public double getQualityPointsFromLetterGrade(){
        double qualityPoints=0.0;
        switch (grade){
            case "A":
                qualityPoints=4.0;
                break;
            case "A-":
                qualityPoints=3.67;
                break;
            case "B+":
                qualityPoints=3.33;
                break;
            case "B":
                qualityPoints=3.0;
                break;
            case "C+":
                qualityPoints=2.33;
                break;
            case "C":
                qualityPoints=2.0;
                break;
            case "C-":
                qualityPoints=1.67;
                break;
            case "D+":
                qualityPoints=1.33;
                break;
            case "D":
                qualityPoints=1.0;
                break;
            case "D-":
                qualityPoints=0.67;
                break;
            case "F":
                qualityPoints=0;
                break;
            default:
                qualityPoints=0;
        }
        return qualityPoints;
    }


    @Override
    public String toString() {
        return "StudentCourse{" +
                ", course=" + course +
                ", grade='" + grade + '\'' +
                ", status=" + status +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
