package com.example.project2.dto;

import lombok.Data;

@Data
public class ScoreDTO {
	private Integer id;
	
	private double score; 
	
	private StudentDTO student;
	
	private CourseDTO course;

}
