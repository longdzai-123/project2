package com.example.project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.dto.GroupDTO;
import com.example.project2.dto.ResponseDTO;
import com.example.project2.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class RESTGroupController {
	@Autowired
	GroupService groupService;
	
	@PostMapping("/")
	public ResponseDTO<GroupDTO> add(@RequestBody @Validated GroupDTO groupDTO){
		groupService.create(groupDTO);
		return ResponseDTO.<GroupDTO>builder().status(200).data(groupDTO).build();
	}
	
	
	

}
