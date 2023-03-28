package com.example.project2.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	
    private Integer id;
    
	@NotBlank // validation 
    private String name;
	
	
    private String avatar;//URL
    private String username;
    private String password;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    
    @JsonIgnore
    private MultipartFile file;
    
    private Date createdAt;
    
    private List<UserRoleDTO> userRoleDTOs;
}
