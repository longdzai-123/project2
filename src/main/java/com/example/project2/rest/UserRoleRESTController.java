package com.example.project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.UserRoleDTO;
import com.example.project2.service.UserRoleService;

@RestController
@RequestMapping("/api/user-role")
public class UserRoleRESTController {
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/new")
	public void add(@ModelAttribute UserRoleDTO userRoleDTO){
		userRoleService.create(userRoleDTO); 
	}
	
	@PostMapping("/new-json") //JSON: javascript object in text form 
	public void create(@RequestBody UserRoleDTO userRoleDTO){
		userRoleService.create(userRoleDTO); 
	}
	
	@GetMapping("/delete") // ?id=1 REST API
	public void delete(@RequestParam("id")int id) {
		userRoleService.delete(id);
	}
	
	@GetMapping("/get/{id}")  // get/10
	public UserRoleDTO get(@PathVariable("id") int id) {
	    return userRoleService.getById(id);
	    //jackson
	}
	@PostMapping("/search")
	public PageDTO<UserRoleDTO> search(
			@RequestParam(name= "role",required = false) String role,
			@RequestParam(name= "userId",required = false) Integer userId, 
			@RequestParam(name="size",required = false) Integer size,
			@RequestParam(name = "page",required = false) Integer page)  {
		
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		role = role == null ? "" : role;
		
		PageDTO<UserRoleDTO> pageRS = new PageDTO<>();
		
		if(userId != null)
			pageRS = userRoleService.searchByIdUser(userId, page, size);
		else 
		    pageRS = userRoleService.searchByRole("%"+role+"%", page, size);
		
	    return pageRS;
    }
}
