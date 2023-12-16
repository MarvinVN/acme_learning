package com.hbp.acme_learning.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.InstructorService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    
    @Autowired
    InstructorService instructorService;

    @PostMapping("/signup")
    public Instructor signUp(@RequestBody SignUpRequest signUpRequest) throws SQLIntegrityConstraintViolationException {
        return instructorService.signUp(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
    }

    @PostMapping("/login")
    public Instructor login(@RequestBody LoginRequest loginRequest) {
        return instructorService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/{instructorId}/create-course")
    public Course createCourse(@PathVariable Long instructorId, @PathVariable String courseName) throws Exception {
        return instructorService.createCourse(instructorId, courseName);
    }

    @PostMapping("/{instructorId}/course/{courseId}/start")
    public void startCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws Exception {
        instructorService.startCourse(instructorId, courseId);
    }

    @PostMapping("/{instructorId}/course/{courseId}/cancel")
    public void cancelCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws Exception {
        instructorService.cancelCourse(instructorId, courseId);
    }

    @GetMapping("/{instructorId}/courses")
    public List<Course> listCourses(@PathVariable Long instructorId) throws Exception {
        return instructorService.listCourses(instructorId);
    }

    @GetMapping("/{instructorId}/course/{courseId}/students")
    public List<Student> listEnrolledStudents(@PathVariable Long instructorId, @PathVariable Long courseId) throws Exception {
        return instructorService.listEnrolledStudents(instructorId, courseId);
    }
    
}
