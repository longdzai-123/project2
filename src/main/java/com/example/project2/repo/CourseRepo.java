package com.example.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{
     
}
