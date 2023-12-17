package com.hbp.acme_learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.InstructorRepository;

@Service
public class InstructorService {
    
    @Autowired
    public InstructorRepository instructorRepository;

    @Autowired
    public CourseService courseService;

    public Instructor signUp(String name, String email, String password) {
        Instructor instructor = new Instructor(name, email, password);
        return instructorRepository.save(instructor);
    }

    public Instructor login(String email, String password) {
        return instructorRepository.findByEmailAndPassword(email, password);
    }

    public Course createCourse(Long instructorId, String courseName) throws Exception {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Instructor not found."));
        Course course = instructor.createCourse(courseName);
        return courseService.createCourse(course);
    }

    public void startCourse(Long instructorId, Long courseId) throws Throwable {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Instructor not found."));
        instructor.startCourse(courseId);
        courseService.startCourse(courseId);
    }

    public void cancelCourse(Long instructorId, Long courseId) throws Throwable {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Instructor not found."));
        instructor.cancelCourse(courseId);
        courseService.cancelCourse(courseId);
    }
    
    public List<Course> listCourses(Long instructorId) throws Exception {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Instructor not found."));
        return instructor.getCourses();
    }

    public List<Student> listEnrolledStudents(Long instructorId, Long courseId) throws Exception {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new Exception("Instructor not found."));
        return instructor.getEnrolledStudents(courseId);
    }

}
