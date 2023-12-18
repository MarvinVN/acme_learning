package com.hbp.acme_learning.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.dto.CourseDTO;
import com.hbp.acme_learning.dto.DTOConverter;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    public CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long courseId) throws Throwable {
        Course course = courseService.getCourseById(courseId); 
        CourseDTO courseDTO = DTOConverter.courseToDTO(course);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses.stream()
                                        .map(DTOConverter::courseToDTO)
                                        .collect(Collectors.toList()), HttpStatus.OK);
    }
    
}
