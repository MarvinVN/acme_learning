package com.hbp.acme_learning.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbp.acme_learning.dto.CourseDTO;
import com.hbp.acme_learning.dto.DTOConverter;
import com.hbp.acme_learning.dto.StudentDTO;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.StudentService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

@WebMvcTest(StudentControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    
    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void signUpTest() throws SQLIntegrityConstraintViolationException {
        
        ResponseEntity<StudentDTO> response;
        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        String password = "password";
        Student expectedStudent = new Student(name, email, password);
        Student resultStudent;

        when(studentService.signUp(anyString(), anyString(), anyString())).thenReturn(expectedStudent);
        response = studentController.signUp(new SignUpRequest(name, email, password));
        resultStudent = DTOConverter.DTOToStudent(response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedStudent.getName(), resultStudent.getName());
        assertEquals(expectedStudent.getEmail(), resultStudent.getEmail());

    }

    @Test
    public void loginTest() {

        ResponseEntity<StudentDTO> response;
        String email = "johnsmith@gmail.com";
        String password = "password";
        Student expectedStudent = new Student("John Smith", email, password);
        Student resultStudent;

        when(studentService.login(anyString(), anyString())).thenReturn(expectedStudent);
        response = studentController.login(new LoginRequest(email, password));
        resultStudent = DTOConverter.DTOToStudent(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudent.getName(), resultStudent.getName());
        assertEquals(expectedStudent.getEmail(), resultStudent.getEmail());

    }

    @Test
    public void enrollCourseTest() throws Throwable {

        ResponseEntity<Void> response;
        Long studentId = 1L;
        Long courseId = 1L;

        response = studentController.enrollCourse(studentId, courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    
    @Test
    public void dropCourseTest() throws Throwable {

        ResponseEntity<Void> response;
        Long studentId = 1L;
        Long courseId = 1L;

        response = studentController.dropCourse(studentId, courseId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    public void listEnrolledCoursesTest() throws Throwable {

        ResponseEntity<List<CourseDTO>> response;
        Long studentId = 1L;
        List<Course> expectedCourses = Arrays.asList(new Course(1L, "Math100", false));
        List<CourseDTO> expectedCourseDTOs = expectedCourses.stream().map(DTOConverter::courseToDTO).collect(Collectors.toList());

        when(studentService.listEnrolledCourses(anyLong())).thenReturn(expectedCourses);
        response = studentController.listEnrolledCourses(studentId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourseDTOs.size(), response.getBody().size());

        for (int i = 0; i < expectedCourseDTOs.size(); i++) {
            assertEquals(expectedCourseDTOs.get(i).getCourseId(), response.getBody().get(i).getCourseId());
            assertEquals(expectedCourseDTOs.get(i).getCourseName(), response.getBody().get(i).getCourseName());
        }
    }

    @Test
    public void listAllCoursesTest() {
        
        ResponseEntity<List<CourseDTO>> response;
        List<Course> expectedCourses = Arrays.asList(new Course(1L, "Math100", false));
        List<CourseDTO> expectedCourseDTOs = expectedCourses.stream().map(DTOConverter::courseToDTO).collect(Collectors.toList());

        when(studentService.listAllCourses()).thenReturn(expectedCourses);
        response = studentController.listAllCourses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourseDTOs.size(), response.getBody().size());

        for (int i = 0; i < expectedCourseDTOs.size(); i++) {
            assertEquals(expectedCourseDTOs.get(i).getCourseId(), response.getBody().get(i).getCourseId());
            assertEquals(expectedCourseDTOs.get(i).getCourseName(), response.getBody().get(i).getCourseName());
        }
    }
}
