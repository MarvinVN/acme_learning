package com.hbp.acme_learning.dto;

import com.hbp.acme_learning.model.Course;
import com.hbp.acme_learning.model.Instructor;
import com.hbp.acme_learning.model.Student;

public class DTOConverter {
    
    public static StudentDTO studentToDTO(Student student) {
        return new StudentDTO(student.getStudentId(), student.getName(), student.getEmail());
    }

    public static InstructorDTO instructorToDTO(Instructor instructor) {
        return new InstructorDTO(instructor.getInstructorId(), instructor.getName(), instructor.getEmail());
    }

    public static CourseDTO courseToDTO(Course course) {
        return new CourseDTO(course.getCourseId(), course.getCourseName(), course.isStarted());
    }

    public static Student DTOToStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getEmail());
    }

    public static Instructor DTOToInstructor(InstructorDTO instructorDTO) {
        return new Instructor(instructorDTO.getInstructorId(), instructorDTO.getName(), instructorDTO.getEmail());
    }

    public static Course DTOToCourse(CourseDTO courseDTO) {
        return new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.isStarted());
    }
}
