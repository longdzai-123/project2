package com.example.project2.dto;

import lombok.Data;

@Data
public class StudentDTO {
    
	private Integer id; //user id
	
	private String studentCode;
	
	private UserDTO user;
}
