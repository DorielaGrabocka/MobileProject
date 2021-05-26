package com.example.models;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String title;
    private String instructor;
    private int credits;
    private String day;
    private String time;
    private String venue;//class in database
    private String department;
    private String faculty;
    private String eligibility; //all or -
    private double midterm_weight;
    private double final_weight;
    private double project_weight;
    private double other_components_weight;

    //empty constructor
    public Course(){

    }

    public Course(int id, String title, String instructor, int credits, String day, String time,
                  String venue, String department, String faculty, String eligibility) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.credits = credits;
        this.day = day;
        this.time = time;
        this.venue = venue;
        this.department = department;
        this.faculty = faculty;
        this.eligibility = eligibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }


    public double getMidterm_weight() {
        return midterm_weight;
    }

    public void setMidterm_weight(double midterm_weight) {
        this.midterm_weight = midterm_weight;
    }

    public double getFinal_weight() {
        return final_weight;
    }

    public void setFinal_weight(double final_weight) {
        this.final_weight = final_weight;
    }

    public double getProject_weight() {
        return project_weight;
    }

    public void setProject_weight(double project_weight) {
        this.project_weight = project_weight;
    }

    public double getOther_components_weight() {
        return other_components_weight;
    }

    public void setOther_components_weight(double other_components_weight) {
        this.other_components_weight = other_components_weight;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor='" + instructor + '\'' +
                ", credits=" + credits +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", department='" + department + '\'' +
                ", faculty='" + faculty + '\'' +
                ", eligibility='" + eligibility + '\'' +
                ", midterm_weight=" + midterm_weight +
                ", final_weight=" + final_weight +
                ", project_weight=" + project_weight +
                ", other_components_weight=" + other_components_weight +
                '}';
    }
}
