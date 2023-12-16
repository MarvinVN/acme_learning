package com.hbp.acme_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbp.acme_learning.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
