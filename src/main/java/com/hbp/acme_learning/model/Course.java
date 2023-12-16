package com.hbp.acme_learning.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "COURSE_TABLE")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    
    @Column
    private String courseName;
    @Column
    private boolean started;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents = new ArrayList<Student>();

    public Course(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.started = false;
    }

    public Long getCourseId(){
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isStarted() {
        return started;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void start() {
        started = true;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
}
