package com.hbp.acme_learning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentTest {
    
    @Test
    public void buildTest() {

        Long studentId = 1L;
        String name = "studentname";
        String email = "email";
        String password = "password";

        Student studentA = new Student(name, email, password);
        Student studentB = new Student(studentId, name, email);

        assertEquals(name, studentA.getName());
        assertEquals(email, studentA.getEmail());
        assertEquals(password, studentA.getPassword());
        assertEquals(studentId, studentB.getStudentId());
        assertEquals(name, studentB.getName());
        assertEquals(email, studentB.getEmail());

    }

    @Test
    public void enrollCourseTest() {
        
        Student student = new Student();
        Course course = new Course();
        List<Course> expectedCourses = Arrays.asList(course);

        student.enrollCourse(course);

        assertEquals(expectedCourses, student.getEnrolledCourses());

    }

    @Test
    public void dropCourseTest() {
        
        Student student = new Student();
        Course course = new Course();

        student.enrollCourse(course);
        assertEquals(1, student.getEnrolledCourses().size());
        student.dropCourse(course);

        assertEquals(0, student.getEnrolledCourses().size());
        
    }

}
