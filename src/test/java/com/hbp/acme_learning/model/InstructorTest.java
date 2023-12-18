package com.hbp.acme_learning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InstructorTest {
    
    @Test
    public void buildTest() {

        Long instructorId = 1L;
        String name = "instructorname";
        String email = "email";
        String password = "password";

        Instructor instructorA = new Instructor(name, email, password);
        Instructor instructorB = new Instructor(instructorId, name, email);

        assertEquals(name, instructorA.getName());
        assertEquals(email, instructorA.getEmail());
        assertEquals(password, instructorA.getPassword());
        assertEquals(instructorId, instructorB.getInstructorId());
        assertEquals(name, instructorB.getName());
        assertEquals(email, instructorB.getEmail());

    }

    @Test
    public void createCourseTest() {
        
        String courseName = "coursename";
        Instructor instructor = new Instructor();

        instructor.createCourse(courseName);

        assertEquals(1, instructor.getCourses().size());

    }
    
    @Test
    public void startCourseTest() throws Exception {
        
        Instructor instructor = new Instructor();
        Course course = new Course(1L, "coursename", false);

        instructor.addCourse(course);
        instructor.startCourse(1L);

        assertTrue(instructor.getCourses().get(0).isStarted());
        
    }

    @Test
    public void cancelCourseTest() throws Exception {
        
        Instructor instructor = new Instructor();

        instructor.addCourse(new Course(1L, "name", false));
        assertEquals(1, instructor.getCourses().size());
        instructor.cancelCourse(1L);

        assertEquals(0, instructor.getCourses().size());

    }

    @Test
    public void getEnrolledStudentsTest() {
        
        Course course = new Course(1L, "coursename", false);
        Student student = new Student();
        Instructor instructor = new Instructor();

        instructor.addCourse(course);
        assertEquals(0, instructor.getCourses().get(0).getEnrolledStudents().size());
        course.enrollStudent(student);
        
        assertEquals(1, instructor.getCourses().get(0).getEnrolledStudents().size());

    }

}
