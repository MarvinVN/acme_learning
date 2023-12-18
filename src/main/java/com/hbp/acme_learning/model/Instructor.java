package com.hbp.acme_learning.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSTRUCTOR_TABLE")
@Getter @Setter @NoArgsConstructor
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();

    public Instructor(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Instructor(Long instructorId, String name, String email) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
    }

    public Course createCourse(String courseName) {
        Course course = new Course(courseName, this);
        courses.add(course);
        return course;
    }

    public void startCourse(Long courseId) throws Exception {
        Course course = getCourseById(courseId);
        course.setStarted(true);
    }

    public void cancelCourse(Long courseId) throws Exception {
        Course course = getCourseById(courseId);
        if(!course.isStarted()) {
            courses.remove(course);
        } else {
            throw new Exception("Cannot cancel in-progress course");
        }
    }

    public List<Student> getEnrolledStudents(Long courseId) throws Exception {
        Course course = getCourseById(courseId);
        return course.getEnrolledStudents();
    }

    private Course getCourseById(Long courseId) throws Exception {
        return courses.stream()
                    .filter(course -> course.getCourseId().equals(courseId))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Course not found."));
    }

    protected void addCourse(Course course) {
        courses.add(course);
    }

}
