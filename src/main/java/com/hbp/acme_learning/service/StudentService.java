package com.hbp.acme_learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    public StudentRepository studentRepository;
    
    @Autowired
    public CourseService courseService;

    public Student signUp(String name, String email, String password) {
        Student student = new Student(name, email, password);
        return (Student) studentRepository.save(student);
    }

    //should return student
    public void login(String email, String password) {
        return;
    }

    public void enrollCourse(Long studentId, Long courseId) throws Throwable {
        Student student = (Student) studentRepository.findById(studentId).orElseThrow(() -> new Exception("Student not found"));
        Course course = courseService.getCourseById(courseId);

        if (!course.isStarted()) {
            student.enrollCourse(course);
        } else {
            throw new Exception("Cannot join in-progress course.");
        }

    }

    public void dropCourse(Long studentId, Long courseId) throws Throwable {
        Student student = (Student) studentRepository.findById(studentId).orElseThrow(() -> new Exception("Student not found"));
        Course course = courseService.getCourseById(courseId);
        student.dropCourse(course);
        studentRepository.save(course);
    }

    public List<Course> listEnrolledCourses(Long studentId) throws Throwable {
        Student student = (Student) studentRepository.findById(studentId).orElseThrow(() -> new Exception("Student not found"));
        return student.getEnrolledCourses();
    }

    public List<Course> listAllCourses() {
        return courseService.getAllCourses();
    }
}
