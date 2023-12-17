package com.hbp.acme_learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.dto.CourseDTO;
import com.hbp.acme_learning.dto.DTOConverter;
import com.hbp.acme_learning.dto.StudentDTO;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.StudentService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @PostMapping("/signup")
    public StudentDTO signUp(@RequestBody SignUpRequest signUpRequest) throws SQLIntegrityConstraintViolationException {
        Student student = studentService.signUp(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        return DTOConverter.studentToDTO(student);
    }

    @PostMapping("/login")
    public StudentDTO login(@RequestBody LoginRequest loginRequest) {
        Student student = studentService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return DTOConverter.studentToDTO(student);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public void enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.enrollCourse(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public void dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.dropCourse(studentId, courseId);
    }
    
    @GetMapping("/{studentId}/enrolled-courses")
    public List<CourseDTO> listEnrolledCourses(@PathVariable Long studentId) throws Throwable {
        List<Course> enrolledCourses = studentService.listEnrolledCourses(studentId);
        return enrolledCourses.stream()
                    .map(DTOConverter::courseToDTO)
                    .collect(Collectors.toList());
    }

    @GetMapping("/all-courses")
    public List<CourseDTO> listAllCourses() {
        List<Course> courses = studentService.listAllCourses();
        return courses.stream()
                    .map(DTOConverter::courseToDTO)
                    .collect(Collectors.toList());
    }
    
}
