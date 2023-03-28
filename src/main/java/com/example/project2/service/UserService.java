package com.example.project2.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.UserDTO;
import com.example.project2.dto.UserRoleDTO;
import com.example.project2.entity.User;
import com.example.project2.entity.UserRole;
import com.example.project2.repo.UserRepo;
import com.example.project2.repo.UserRoleRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	@Autowired
	CacheManager cacheManager;
	
	@Transactional
	public  void create(UserDTO userDTO) {
		User user =  new ModelMapper().map(userDTO, User.class);
		// convert  dto -> entity 
//		user.setName(userDTO.getName());
//		user.setUsername(userDTO.getUsername());
//		user.setBirthdate(userDTO.getBirthdate());
//		user.setPassword(userDTO.getPassword());
//		user.setAvatar(userDTO.getAvatar());
		
		userRepo.save(user);
		
		List<UserRoleDTO> userRoleDTOs = userDTO.getUserRoleDTOs(); 
		
	    for(UserRoleDTO userRoleDTO : userRoleDTOs) {
	    	
	    	if(userRoleDTO.getRole() != null) {
	    		UserRole userRole = new UserRole();
	    		userRole.setId(user.getId());
	    		userRole.setRole(userRoleDTO.getRole());
	    		userRole.setUser(user);
	    		
	    		userRoleRepo.save(userRole);
	    	}
	    }
	}
	
	@Transactional
	@CacheEvict(cacheNames = "users", key="#id")
	public  void update(UserDTO userDTO) {
		
		User user =  userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setBirthdate(userDTO.getBirthdate());
		user.setAvatar(userDTO.getAvatar());
	
		userRepo.save(user);		
	}
	
	@Transactional
	@CacheEvict(cacheNames = "users", key="#id")
	public  void updatePassword(UserDTO userDTO) {
		User user =  userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		user.setPassword(userDTO.getPassword());	
		userRepo.save(user);	
	}
		
	@Transactional
	@CacheEvict(cacheNames = "users", key="#id")
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	@Transactional
//	@CacheEvict(cacheNames = "users", key="#id")
	public void deleteAll(List<Integer> ids) {
		userRepo.deleteAllById(ids);
		for(int id : ids) {
			cacheManager.getCache("users").evict(id);
		}
	}
	
	@Cacheable(cacheNames = "users", unless="#result == null")
	public UserDTO getById(int id) {
 		System.out.println("NO CACHE");
		User user = userRepo.findById(id).orElseThrow(NoResultException::new);// java8 lambda
		UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
		//modelmapper
//		userDTO.setName(user.getName());
//		userDTO.setUsername(user.getUsername());
//		userDTO.setBirthdate(user.getBirthdate());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAvatar(user.getAvatar());
//		userDTO.setCreatedAt(user.getCreatedAt());
		return userDTO;
	}
	
	public PageDTO<UserDTO> searchByName(String name, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<User> pageRS = userRepo.searchByName("%" + name + "%", pageable);
 	    
		PageDTO<UserDTO> pageDTO = new PageDTO<UserDTO>();
		
		pageDTO.setTotalPages(pageRS.getTotalPages());
		pageDTO.setTotalElements(pageRS.getTotalElements());
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for(User user : pageRS.getContent()) {
			userDTOs.add(new ModelMapper().map(user, UserDTO.class));
		}
		
		pageDTO.setContents(userDTOs);// set vao pagedto
			
		return pageDTO;
	}
	
}
	

