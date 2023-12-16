package com.hbp.acme_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbp.acme_learning.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
    public Instructor findByEmailAndPassword(String email, String password);

}
