package com.example.project2.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.UserDTO;
import com.example.project2.repo.UserRepo;
import com.example.project2.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	UserRepo userRepo;
	@Autowired 
	UserService userService;
	
	
	@GetMapping("/new")
	public String add() {
		return "user/add.html";
	}
	@PostMapping("/new")
	public String add(@ModelAttribute UserDTO u, Model model) throws IllegalStateException, IOException{
		
		if(u.getFile() != null && !u.getFile().isEmpty()) {
			
			final String UPLOAD_FOLDER = "D:/file/";
			
			String filename = u.getFile().getOriginalFilename();
			File newFile = new File(UPLOAD_FOLDER+filename);
			u.getFile().transferTo(newFile);
			
			u.setAvatar(filename); // save to DB 
		}
	    userService.create(u);
	    
		return "redirect:/user/search";
	}
	@GetMapping("/delete") // ?id=1
	public String delete(@RequestParam("id")int id) {
		userService.delete(id);
		return "redirect:/user/search";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model ) {
		model.addAttribute("user", userService.getById(id));
		return "user/edit.html";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute UserDTO editUser) throws IllegalStateException, IOException {
		if(!editUser.getFile().isEmpty()) {
			final String UPLOAD_FOLDER = "D:/file/";
			String filename = editUser.getFile().getOriginalFilename();
			File newFile = new File(UPLOAD_FOLDER + filename);
			
			editUser.getFile().transferTo(newFile);
			
			editUser.setAvatar(filename);
		}
		
		// lay du lieu can update tu edit qua current, de tranh mat du lieu 
		userService.update(editUser);
		
		return "redirect/user/search";	
	} 

	// /user/download?filename=abc.jpg
	@GetMapping("/download")
	public void download(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
		final String UPLOAD_FOLDER = "D:/file/";
		
		File file = new File(UPLOAD_FOLDER + filename);
		
		// java.nio.file.Files
		Files.copy(file.toPath(), response.getOutputStream());
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name ="id", required = false) Integer id, @RequestParam(name= "name",required = false) String name , @RequestParam(name="start",required = false )@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date start,@RequestParam(name="end",required = false )@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date end, @RequestParam(name="size",required = false) Integer size,@RequestParam(name = "page",required = false) Integer page,Model model )  {
		
		size = size == null ? 10 : size;
		page = page == null ? 0 : page;
		name = name == null ? "" : name;

		
		PageDTO<UserDTO> pageRS = userService.searchByName("%" + name + "%",page, size);
		model.addAttribute("totalPage", pageRS.getTotalPages());
		model.addAttribute("count", pageRS.getTotalElements());
		model.addAttribute("userList", pageRS.getContents());
		
//		// luu lai du lieu set sang view lai
//		model.addAttribute("id", id);
//		model.addAttribute("name", name);
//		model.addAttribute("start", start);
//		model.addAttribute("end", end);
//
//		model.addAttribute("page", page);
//		model.addAttribute("size", size);
		
		return "user/search.html";
	}

}
