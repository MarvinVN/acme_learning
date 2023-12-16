package com.hbp.acme_learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.StudentService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @GetMapping
    public String test() {
        return "endpoint reached";
    }
    

    @PostMapping("/signup")
    public Student signUp(@RequestBody SignUpRequest signUpRequest) throws SQLIntegrityConstraintViolationException {
        return studentService.signUp(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
    }

    @PostMapping("/login")
    public Student login(@RequestBody LoginRequest loginRequest) {
        return studentService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public void enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.enrollCourse(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/drop{courseId}/")
    public void dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.dropCourse(studentId, courseId);
    }
    
    @GetMapping("/{studentId}/enrolled-courses")
    public List<Course> listEnrolledCourses(@PathVariable Long studentId) throws Throwable {
        return studentService.listEnrolledCourses(studentId);
    }

    @GetMapping("/all-courses")
    public List<Course> listAllCourses() {
        return studentService.listAllCourses();
    }
    
}
