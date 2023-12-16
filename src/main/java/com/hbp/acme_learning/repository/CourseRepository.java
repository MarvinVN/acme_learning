package com.hbp.acme_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hbp.acme_learning.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
