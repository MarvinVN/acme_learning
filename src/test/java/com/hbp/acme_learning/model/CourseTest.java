package com.hbp.acme_learning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseTest {
    
    @Test
    public void buildTest() {
        
        Long courseId = 1L;
        String courseName = "coursename";
        boolean started = false;
        Instructor instructor = new Instructor(1L, "john smith", "email");

        Course courseA = new Course(courseName, instructor);
        Course courseB = new Course(courseId, courseName, started);

        assertEquals(courseName, courseA.getCourseName());
        assertEquals(instructor, courseA.getInstructor());
        assertEquals(courseId, courseB.getCourseId());
        assertEquals(courseName, courseB.getCourseName());
        assertEquals(started, courseB.isStarted());

    }

    @Test
    public void enrollCourseTest() {

        Course course = new Course(1L, "coursename", false);
        Student student = new Student();
        List<Student> expectedStudents = Arrays.asList(student);

        course.enrollStudent(student);

        assertEquals(expectedStudents, course.getEnrolledStudents());

    }

    @Test
    public void removeStudentTest() {
        
        Course course = new Course(1L, "coursename", false);
        Student student = new Student();

        course.enrollStudent(student);
        assertEquals(1, course.getEnrolledStudents().size());
        course.removeStudent(student);

        assertEquals(0, course.getEnrolledStudents().size());

    }

}
