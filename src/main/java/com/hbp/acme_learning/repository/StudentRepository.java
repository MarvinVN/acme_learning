package com.hbp.acme_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hbp.acme_learning.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
