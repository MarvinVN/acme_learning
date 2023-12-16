package com.hbp.acme_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    public CourseService courseService;

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId) throws Throwable {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    
}
