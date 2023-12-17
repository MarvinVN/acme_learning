package com.hbp.acme_learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    public CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long courseId) throws Throwable {
        return courseRepository.findById(courseId).orElseThrow(() -> new Exception("Course not found."));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void startCourse(Long courseId) throws Throwable {
        Course course = getCourseById(courseId);
        course.setStarted(true);
        courseRepository.save(course);
    }

    public void cancelCourse(Long courseId) throws Throwable {
        Course course = getCourseById(courseId);
        if (!course.isStarted()) {
            List<Student> enrolledStudents = getEnrolledStudents(courseId);
            enrolledStudents.stream()
                            .forEach(student -> student.dropCourse(course));
            courseRepository.delete(course);
        } else {
            throw new Exception("Cannot cancel in-progress class.");
        }
    }

    public List<Student> getEnrolledStudents(Long courseId) throws Throwable {
        Course course = getCourseById(courseId);
        return course.getEnrolledStudents();
    }

}
