package com.hbp.acme_learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CourseDTO {
    
    private Long courseId;
    private String courseName;
    private boolean started;
    
}
