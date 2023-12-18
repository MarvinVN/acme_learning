package com.hbp.acme_learning.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.dto.CourseDTO;
import com.hbp.acme_learning.dto.DTOConverter;
import com.hbp.acme_learning.dto.InstructorDTO;
import com.hbp.acme_learning.dto.StudentDTO;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.InstructorService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<InstructorDTO> signUp(@RequestBody SignUpRequest signUpRequest) throws SQLIntegrityConstraintViolationException {
        Instructor instructor = instructorService.signUp(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        InstructorDTO instructorDTO = DTOConverter.instructorToDTO(instructor);
        return new ResponseEntity<>(instructorDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<InstructorDTO> login(@RequestBody LoginRequest loginRequest) {
        Instructor instructor = instructorService.login(loginRequest.getEmail(), loginRequest.getPassword());
        InstructorDTO instructorDTO = DTOConverter.instructorToDTO(instructor);
        return new ResponseEntity<>(instructorDTO, HttpStatus.OK);
    }

    @PostMapping("/{instructorId}/create-course/{courseName}")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable Long instructorId, @PathVariable String courseName) throws Exception {
        Course course = instructorService.createCourse(instructorId, courseName);
        CourseDTO courseDTO = DTOConverter.courseToDTO(course);
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{instructorId}/course/{courseId}/start")
    public ResponseEntity<Void> startCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws Throwable {
        instructorService.startCourse(instructorId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{instructorId}/course/{courseId}/cancel")
    public ResponseEntity<Void> cancelCourse(@PathVariable Long instructorId, @PathVariable Long courseId) throws Throwable {
        instructorService.cancelCourse(instructorId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{instructorId}/courses")
    public ResponseEntity<List<CourseDTO>> listCourses(@PathVariable Long instructorId) throws Exception {
        List<Course> courses = instructorService.listCourses(instructorId);
        return new ResponseEntity<>(courses.stream()
                                        .map(DTOConverter::courseToDTO)
                                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{instructorId}/course/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> listEnrolledStudents(@PathVariable Long instructorId, @PathVariable Long courseId) throws Exception {
        List<Student> enrolledStudents = instructorService.listEnrolledStudents(instructorId, courseId);
        return new ResponseEntity<>(enrolledStudents.stream()
                                                .map(DTOConverter::studentToDTO)
                                                .collect(Collectors.toList()), HttpStatus.OK);
    }
    
}
