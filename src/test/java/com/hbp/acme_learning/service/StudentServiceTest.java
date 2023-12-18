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
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    
    @Mock
    StudentRepository studentRepository;

    @Mock
    CourseService courseService;

    @InjectMocks
    StudentService studentService;

    @Test
    public void signUpTest() {
        
        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        String password = "password";
        Student expectedStudent = new Student(name, email, password);
        Student resultStudent;

        when(studentRepository.save(any())).thenReturn(expectedStudent);
        resultStudent = studentService.signUp(name, email, password);

        verify(studentRepository, times(1)).save(any());
        assertEquals(expectedStudent, resultStudent);

    }

    @Test
    public void loginTest() {

        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        Student expectedStudent = new Student(1L, name, email);
        Student resultStudent;

        when(studentRepository.findByEmailAndPassword(any(), any())).thenReturn(expectedStudent);
        resultStudent = studentService.login(name, email);

        verify(studentRepository, times(1)).findByEmailAndPassword(any(), any());
        assertEquals(expectedStudent, resultStudent);

    }

    @Test
    public void enrollCourseTest() throws Throwable {

        Long studentId = 1L;
        Long courseId = 1L;

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(courseService.getCourseById(anyLong())).thenReturn(new Course());
        studentService.enrollCourse(studentId, courseId);

        verify(studentRepository, times(1)).save(any());

    }

    @Test
    public void dropCourseTest() throws Throwable {

        Long studentId = 1L;
        Long courseId = 1L;

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(courseService.getCourseById(anyLong())).thenReturn(new Course());
        studentService.dropCourse(studentId, courseId);

        verify(studentRepository, times(1)).save(any());

    }

    @Test
    public void listEnrolledCoursesTest() throws Throwable {

        Long studentId = 1L;
        Student student = new Student(studentId, "John Smith", "johnsmith@gmail.com");
        Course course = new Course(1L, "MATH100", false);
        List<Course> expectedCourses = Arrays.asList(course);
        List<Course> resultCourses;

        student.enrollCourse(course);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        resultCourses = studentService.listEnrolledCourses(studentId);

        assertEquals(expectedCourses, resultCourses);

    }

    @Test
    public void listAllCoursesTest() {

        List<Course> resultCourses = studentService.listAllCourses();

        verify(courseService, times(1)).getAllCourses();

    }

}
