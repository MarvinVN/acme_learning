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
import com.hbp.acme_learning.dto.InstructorDTO;
import com.hbp.acme_learning.dto.StudentDTO;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;
import com.hbp.acme_learning.service.InstructorService;
import com.hbp.acme_learning.utils.LoginRequest;
import com.hbp.acme_learning.utils.SignUpRequest;

@WebMvcTest(InstructorControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class InstructorControllerTest {
    
    @MockBean
    private InstructorService instructorService;
    
    @InjectMocks
    private InstructorController instructorController;

    @Test
    public void signUpTest() throws SQLIntegrityConstraintViolationException {
        
        ResponseEntity<InstructorDTO> response;
        String name = "John Smith";
        String email = "johnsmith@gmail.com";
        String password = "password";
        Instructor expectedInstructor = new Instructor(name, email, password);
        Instructor resultInstructor;

        when(instructorService.signUp(anyString(), anyString(), anyString())).thenReturn(expectedInstructor);
        response = instructorController.signUp(new SignUpRequest(name, email, password));
        resultInstructor = DTOConverter.DTOToInstructor(response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedInstructor.getName(), resultInstructor.getName());
        assertEquals(expectedInstructor.getEmail(), resultInstructor.getEmail());

    }

    @Test
    public void loginTest() {

        ResponseEntity<InstructorDTO> response;
        String email = "johnsmith@gmail.com";
        String password = "password";
        Instructor expectedInstructor = new Instructor("John Smith", email, password);
        Instructor resultInstructor;

        when(instructorService.login(anyString(), anyString())).thenReturn(expectedInstructor);
        response = instructorController.login(new LoginRequest(email, password));
        resultInstructor = DTOConverter.DTOToInstructor(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedInstructor.getName(), resultInstructor.getName());
        assertEquals(expectedInstructor.getEmail(), resultInstructor.getEmail());

    }

    @Test
    public void createCourseTest() throws Exception {

        ResponseEntity<CourseDTO> response;
        Long courseId = 1L;
        String courseName = "MATH100";
        Course expectedCourse = new Course(courseId, courseName, false);
        Course resultCourse;

        when(instructorService.createCourse(courseId, courseName)).thenReturn(expectedCourse);
        response = instructorController.createCourse(courseId, courseName);
        resultCourse = DTOConverter.DTOToCourse(response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedCourse.getCourseId(), resultCourse.getCourseId());
        assertEquals(expectedCourse.getCourseName(), resultCourse.getCourseName());

    }

    @Test
    public void startCourseTest() throws Throwable{

        ResponseEntity<Void> response;
        Long instructorId = 1L;
        Long courseId = 1L;

        response = instructorController.startCourse(instructorId, courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void cancelCourseTest() throws Throwable {

        ResponseEntity<Void> response;
        Long instructorId = 1L;
        Long courseId = 1L;

        response = instructorController.cancelCourse(instructorId, courseId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void listCoursesTest() throws Throwable {
        
        ResponseEntity<List<CourseDTO>> response;
        Long instructorId = 1L;
        List<Course> expectedCourses = Arrays.asList(new Course(1L, "MATH100", true));
        List<CourseDTO> expectedCourseDTOs = expectedCourses.stream().map(DTOConverter::courseToDTO).collect(Collectors.toList());

        when(instructorService.listCourses(anyLong())).thenReturn(expectedCourses);
        response = instructorController.listCourses(instructorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourseDTOs.size(), response.getBody().size());

        for (int i = 0; i < expectedCourseDTOs.size(); i++) {
            assertEquals(expectedCourseDTOs.get(i).getCourseId(), response.getBody().get(i).getCourseId());
            assertEquals(expectedCourseDTOs.get(i).getCourseName(), response.getBody().get(i).getCourseName());
        }

    }

    @Test
    public void listEnrolledStudentsTest() throws Throwable {

        ResponseEntity<List<StudentDTO>> response;
        Long instructorId = 1L;
        Long courseId = 1L;
        List<Student> expectedStudents = Arrays.asList(new Student(1L, "John Smith", "johnsmith@gmail.com"));
        List<StudentDTO> expectedStudentDTOs = expectedStudents.stream().map(DTOConverter::studentToDTO).collect(Collectors.toList());

        when(instructorService.listEnrolledStudents(instructorId, courseId)).thenReturn(expectedStudents);
        response = instructorController.listEnrolledStudents(instructorId, courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudentDTOs.size(), response.getBody().size());

        for (int i = 0; i < expectedStudentDTOs.size(); i++) {
            assertEquals(expectedStudentDTOs.get(i).getStudentId(), response.getBody().get(i).getStudentId());
            assertEquals(expectedStudentDTOs.get(i).getName(), response.getBody().get(i).getName());
            assertEquals(expectedStudentDTOs.get(i).getEmail(), response.getBody().get(i).getEmail());
        }
    }
}
