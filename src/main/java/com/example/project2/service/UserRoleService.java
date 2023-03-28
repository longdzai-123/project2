package com.example.project2.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.UserRoleDTO;
import com.example.project2.entity.User;
import com.example.project2.entity.UserRole;
import com.example.project2.repo.UserRepo;
import com.example.project2.repo.UserRoleRepo;

@Service
public class UserRoleService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;

	@Transactional
	public void create(UserRoleDTO userRoleDTO) {
		UserRole userRole = new UserRole();
		userRole.setRole(userRoleDTO.getRole());

		User user = userRepo.findById(userRoleDTO.getUserId()).orElseThrow(NoResultException::new);

		userRole.setUser(user);

		userRoleRepo.save(userRole);
	}

	@Transactional
	public void update(UserRoleDTO userRoleDTO) {
		
		UserRole userRole = userRoleRepo.findById(userRoleDTO.getId()).orElseThrow(NoResultException::new);
		userRole.setRole(userRoleDTO.getRole());
		userRoleRepo.save(userRole);
	}
	@Transactional
	public void delete(int id) {
		userRoleRepo.deleteById(id); // delete by user role id 
	}
	@Transactional
	public void deleteAll(List<Integer> ids) {
		userRoleRepo.deleteAllById(ids);      // delete by user role id 
	}
	
	
	@Transactional
	public void deleteByUserid(int userid) {
		userRoleRepo.delteByUserId(userid);
	}
	
	public UserRoleDTO getById(int id) {
        UserRole userRole = userRoleRepo.findById(id).orElseThrow(NoResultException::new);
        
        return new ModelMapper().map(userRole, UserRoleDTO.class);
	}
	
	public PageDTO<UserRoleDTO> searchByIdUser(int userid, int page , int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<UserRole> pageRS = userRoleRepo.SearchByUserId(userid, pageable);
		
		PageDTO<UserRoleDTO> pageDTO = new PageDTO<>();
		
		pageDTO.setTotalPages(pageRS.getTotalPages());
		
		pageDTO.setTotalElements(pageRS.getTotalElements());
		
		List<UserRoleDTO>  userRoleDTOs = new ArrayList<UserRoleDTO>();
		
		for(UserRole userRole : pageRS.getContent()) {			
			userRoleDTOs.add(new ModelMapper().map(userRole, UserRoleDTO.class));
		}
		pageDTO.setContents(userRoleDTOs);
		
		return pageDTO;
	
	}
	
	public PageDTO<UserRoleDTO> searchByRole(String role, int page , int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<UserRole> pageRS = userRoleRepo.SearchByRole(role, pageable);
		
		PageDTO<UserRoleDTO> pageDTO = new PageDTO<>();
		
		pageDTO.setTotalPages(pageRS.getTotalPages());
		
		pageDTO.setTotalElements(pageRS.getTotalElements());
		
		List<UserRoleDTO>  userRoleDTOs = new ArrayList<UserRoleDTO>();
		
		for(UserRole userRole : pageRS.getContent()) {			
			userRoleDTOs.add(new ModelMapper().map(userRole, UserRoleDTO.class));
		}
		pageDTO.setContents(userRoleDTOs);
		
		return pageDTO;
	
	}
}
