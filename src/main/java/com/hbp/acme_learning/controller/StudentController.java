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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<StudentDTO> signUp(@RequestBody SignUpRequest signUpRequest) throws SQLIntegrityConstraintViolationException {
        Student student = studentService.signUp(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        StudentDTO studentDTO = DTOConverter.studentToDTO(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<StudentDTO> login(@RequestBody LoginRequest loginRequest) {
        Student student = studentService.login(loginRequest.getEmail(), loginRequest.getPassword());
        StudentDTO studentDTO = DTOConverter.studentToDTO(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Void> enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.enrollCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public ResponseEntity<Void> dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) throws Throwable {
        studentService.dropCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{studentId}/enrolled-courses")
    public ResponseEntity<List<CourseDTO>> listEnrolledCourses(@PathVariable Long studentId) throws Throwable {
        List<Course> enrolledCourses = studentService.listEnrolledCourses(studentId);
        return new ResponseEntity<>(enrolledCourses.stream()
                                                .map(DTOConverter::courseToDTO)
                                                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/all-courses")
    public ResponseEntity<List<CourseDTO>> listAllCourses() {
        List<Course> courses = studentService.listAllCourses();
        return new ResponseEntity<>(courses.stream()
                                        .map(DTOConverter::courseToDTO)
                                        .collect(Collectors.toList()), HttpStatus.OK);
    }
    
}
