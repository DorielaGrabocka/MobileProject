package com.example.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.activities.MainActivity;
import com.example.database.implementations.StudentDAOImplementation;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Student implements Serializable {
    //from the database
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String major;
    private String minor;
    private String password;

    //to be computed
    private int credits=-1;
    private List<StudentCourse> listOfFinishedCourses;
    private List<Course> listOfNextPossibleCourses;
    private List<Course> listOfCurrentCourses;
    private double gpa;

    public Student() {
    }

    public Student(int id, String name, String surname, int age, String email, String major, String minor, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.major = major;
        this.minor = minor;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getCredits() {
        return credits ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setCredits() {
        this.credits = listOfFinishedCourses.stream()
                .map(sc-> sc.getCourse().getCredits())
                .reduce((x,y)-> x+y)
                .orElse(-1);
    }

    public List<StudentCourse> getListOfFinishedCourses() {
        return listOfFinishedCourses;
    }

    public void setListOfFinishedCourses(List<StudentCourse> listOfFinishedCourses) {
        this.listOfFinishedCourses = listOfFinishedCourses;
    }

    public List<Course> getListOfNextPossibleCourses() {
        return listOfNextPossibleCourses;
    }

    public void setListOfNextPossibleCourses(List<Course> listOfNextPossibleCourses) {
        this.listOfNextPossibleCourses = listOfNextPossibleCourses;
    }

    public List<Course> getListOfCurrentCourses() {
        return listOfCurrentCourses;
    }

    public void setListOfCurrentCourses(List<Course> listOfCurrentCourses) {
        this.listOfCurrentCourses = listOfCurrentCourses;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double getGpa() {
        return gpa;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setGpa() {
        if(credits==-1)setCredits();
        double totalQualityPoints = listOfFinishedCourses.stream()
                .map(sc->sc.getCourse().getCredits()*sc.getQualityPointsFromLetterGrade())
                .reduce((x,y)->x+y)
                .orElse(Double.valueOf(0));
        gpa = totalQualityPoints/credits;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", minor='" + minor + '\'' +
                ", password='" + password + '\'' +
                ", credits=" + credits +
                ", listOfFinishedCourses=" + listOfFinishedCourses +
                ", listOfNextPossibleCourses=" + listOfNextPossibleCourses +
                ", listOfCurrentCourses=" + listOfCurrentCourses +
                ", gpa=" + gpa +
                '}';
    }
}
