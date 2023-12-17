package com.hbp.acme_learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class InstructorDTO {
    
    private Long instructorId;
    private String name;
    private String email;

}
