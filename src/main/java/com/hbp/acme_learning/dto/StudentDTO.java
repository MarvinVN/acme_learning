package com.hbp.acme_learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class StudentDTO {
    
    private Long studentId;
    private String name;
    private String email;
    
}
