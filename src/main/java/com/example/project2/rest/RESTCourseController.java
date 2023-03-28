package com.example.project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.dto.CourseDTO;
import com.example.project2.dto.ResponseDTO;
import com.example.project2.service.CourseService;

@RestController
@RequestMapping("api/course")
public class RESTCourseController {
	@Autowired
	CourseService courseService;
     
	@PostMapping("/")
	public ResponseDTO<CourseDTO> add(@RequestBody CourseDTO courseDTO){
		courseService.create(courseDTO);
	   	return ResponseDTO.<CourseDTO>builder().status(200).data(courseDTO).build();
	}
}
