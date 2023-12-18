package com.hbp.acme_learning.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hbp.acme_learning.dto.CourseDTO;
import com.hbp.acme_learning.dto.DTOConverter;
import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.service.CourseService;

@WebMvcTest(CourseControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
    
    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    @Test
    public void getCourse() throws Throwable {

        ResponseEntity<CourseDTO> response;
        Long courseId = 1L;
        Course expectedCourse = new Course(1L, "MATH100", false);

        when(courseService.getCourseById(anyLong())).thenReturn(expectedCourse);
        response = courseController.getCourse(courseId);
        Course resultCourse = DTOConverter.DTOToCourse(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourse.getCourseId(), resultCourse.getCourseId());
        assertEquals(expectedCourse.getCourseName(), resultCourse.getCourseName());

    }

    @Test
    public void getAllCourses() {
    
        ResponseEntity<List<CourseDTO>> response;
        List<Course> expectedCourses = Arrays.asList(new Course(1L, "MATH100", false));
        List<CourseDTO> expectedCourseDTOs = expectedCourses.stream().map(DTOConverter::courseToDTO).collect(Collectors.toList());

        when(courseService.getAllCourses()).thenReturn(expectedCourses);
        response = courseController.getAllCourses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourseDTOs.size(), response.getBody().size());

        for (int i = 0; i < expectedCourseDTOs.size(); i++) {
            assertEquals(expectedCourseDTOs.get(i).getCourseId(), response.getBody().get(i).getCourseId());
            assertEquals(expectedCourseDTOs.get(i).getCourseName(), response.getBody().get(i).getCourseName());
        }
    }

}
