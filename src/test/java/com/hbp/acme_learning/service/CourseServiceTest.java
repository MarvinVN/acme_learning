package com.hbp.acme_learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    
    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    CourseService courseService;

    @Test
    public void createCourseTest() {

        Course expectedCourse = new Course();
        Course resultCourse;

        when(courseRepository.save(any())).thenReturn(expectedCourse);
        resultCourse = courseService.createCourse(expectedCourse);

        verify(courseRepository, times(1)).save(any());
        assertEquals(expectedCourse, resultCourse);

    }

    @Test
    public void getCourseByIdTest() throws Throwable {

        Long courseId = 1L;
        Course expectedCourse = new Course();
        Course resultCourse;

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(expectedCourse));
        resultCourse = courseService.getCourseById(courseId);

        verify(courseRepository, times(1)).findById(courseId);
        assertEquals(expectedCourse, resultCourse);

    }
    
    @Test
    public void getAllCoursesTest() {

        List<Course> expectedCourses = Arrays.asList(new Course());
        List<Course> resultCourses;

        when(courseRepository.findAll()).thenReturn(expectedCourses);
        resultCourses = courseService.getAllCourses();

        verify(courseRepository, times(1)).findAll();
        assertEquals(expectedCourses, resultCourses);

    }
    
    @Test
    public void startCourseTest() throws Throwable {

        Long courseId = 1L;
        Course course = new Course(courseId, "NAME", false);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(courseRepository.save(any())).thenReturn(course);
        courseService.startCourse(courseId);

        verify(courseRepository, times(1)).findById(anyLong());
        verify(courseRepository, times(1)).save(any());
        assertTrue(course.isStarted());

    }
    
    @Test
    public void cancelCourseTest() throws Throwable {

        Long courseId = 1L;
        Course course = new Course(courseId, "NAME", false);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        courseService.cancelCourse(courseId);

        verify(courseRepository, times(1)).delete(course);

    }
    
    @Test
    public void getEnrolledStudentsTest() throws Throwable {

        /* 
        Long courseId = 1L;
        Course course = new Course(courseId, "NAME", false);
        Student student = new Student(courseId, "name", "email");
        List<Student> expectedStudents = Arrays.asList(student);
        List<Student> resultStudents;

        student.enrollCourse(course);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        resultStudents = courseService.getEnrolledStudents(courseId);

        assertEquals(expectedStudents, resultStudents);
        */

    }
    

}
