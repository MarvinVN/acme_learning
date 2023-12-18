package com.hbp.acme_learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.CourseRepository;
import com.hbp.acme_learning.repository.InstructorRepository;

@WebMvcTest(InstructorServiceTest.class)
@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {
    
    @Mock
    InstructorRepository instructorRepository;

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    InstructorService instructorService;

    @Test
    public void signUpTest() {
        
        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        String password = "password";
        Instructor expectedInstructor = new Instructor(name, email, password);
        Instructor resultInstructor;

        when(instructorRepository.save(any())).thenReturn(expectedInstructor);
        resultInstructor = instructorService.signUp(name, email, password);

        verify(instructorRepository, times(1)).save(any());
        assertEquals(expectedInstructor, resultInstructor);

    }

    @Test
    public void loginTest() {
        
        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        Instructor expectedInstructor = new Instructor(1L, name, email);
        Instructor resultInstructor;

        when(instructorRepository.findByEmailAndPassword(any(), any())).thenReturn(expectedInstructor);
        resultInstructor = instructorService.login(name, email);

        verify(instructorRepository, times(1)).findByEmailAndPassword(any(), any());
        assertEquals(expectedInstructor, resultInstructor);

    }

    @Test
    public void createCourseTest() throws Exception {
        
        /*
        Long instructorId = 1L;
        String courseName = "MATH100";
        Course expectedCourse = new Course(1L, courseName, false);
        Course resultCourse;

        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(new Instructor()));
        when(courseRepository.save(any())).thenReturn(expectedCourse);
        resultCourse = instructorService.createCourse(instructorId, courseName);

        verify(courseRepository, times(1)).save(any());
        assertEquals(expectedCourse, resultCourse);
        */

    }

    @Test
    public void startCourseTest() throws Throwable {
        
        /*
        Long instructorId = 1L;
        Long courseId = 1L;

        Instructor instructor = new Instructor(1L, "name", "email");
        Course course = instructor.createCourse("COURSE");

        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        instructorService.startCourse(instructorId, courseId);

        verify(courseRepository, times(1)).save(any());
        assertTrue(course.isStarted());
        */

    }

    @Test
    public void cancelCourseTest() throws Throwable {
        
        /*
        Long instructorId = 1L;
        Long courseId = 1L;

        Instructor instructor = new Instructor(instructorId, "name", "email");
        Course course = instructor.createCourse("COURSE");

        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        instructorService.cancelCourse(instructorId, courseId);

        verify(courseRepository, times(1)).delete(any());
        assertEquals(0, instructor.getCourses().size());
        */

    }

    @Test
    public void listCoursesTest() throws Exception {
        
        Long instructorId = 1L;
        Instructor instructor = new Instructor(instructorId, "name", "email");
        List<Course> resultCourses;

        instructor.createCourse("COURSE");
        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));
        resultCourses = instructorService.listCourses(instructorId);

        assertEquals(1, resultCourses.size());

    }

    @Test
    public void listEnrolledStudentsTest() {
        
    }

}
