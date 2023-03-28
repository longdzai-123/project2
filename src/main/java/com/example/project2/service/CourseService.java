package com.example.project2.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.dto.CourseDTO;
import com.example.project2.entity.Course;
import com.example.project2.repo.CourseRepo;

@Service
public class CourseService {
	@Autowired
	CourseRepo courseRepo;
    
	@Transactional
	 public void create(CourseDTO courseDTO) {
		Course course = new ModelMapper().map(courseDTO, Course.class);
		courseRepo.save(course);
	}
}
