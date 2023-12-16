package com.hbp.acme_learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.StudentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @PostMapping("/signup")
    public Student signUp(@RequestBody Student student) {
        System.out.println(student); 
        return studentService.signUp(student.getName(), student.getEmail(), student.getPassword());
    }

    @PostMapping("/login")
    public void login(@RequestBody Student student) {
        return;
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public void enrollCourse(@RequestBody String entity) {
        return;
    }

    @DeleteMapping("/{studentId}/drop{courseId}/")
    public void dropCourse(@RequestBody String entity) {
        return;
    }
    
    @GetMapping("/{studentId}/enrolled-courses")
    public void listEnrolledCourses(@RequestParam String param) {
        return;
    }

    @GetMapping("/all-courses")
    public void listAllCourses(@RequestParam String param) {
        return;
    }
    
}
