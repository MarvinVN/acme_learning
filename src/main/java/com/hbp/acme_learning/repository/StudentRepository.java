package com.hbp.acme_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbp.acme_learning.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByEmailAndPassword(String email, String password);
    
}
