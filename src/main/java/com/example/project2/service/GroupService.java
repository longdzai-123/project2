package com.example.project2.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.dto.GroupDTO;
import com.example.project2.dto.UserDTO;
import com.example.project2.entity.Group;
import com.example.project2.entity.User;
import com.example.project2.repo.GroupRepo;
import com.example.project2.repo.UserRepo;

@Service
public class GroupService {
	
   @Autowired
   GroupRepo groupRepo;
   
   @Autowired
   UserRepo userRepo;
   
   @Transactional
   public void create(GroupDTO groupDTO) {
	   Group group = new Group();
	   group.setName(groupDTO.getName());
	   
	   List<User> users = new ArrayList<>();
	   
	   for(UserDTO userDTO : groupDTO.getUsers()) {
		   User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		   users.add(user);
	   }	   
	   group.setUsers(users);
	   
	   groupRepo.save(group);
   }
   
   @Transactional
   public void update(GroupDTO groupDTO) {
	   Group group = groupRepo.findById(groupDTO.getId()).orElseThrow(NoResultException::new);
	   group.setName(groupDTO.getName());
	   // neu co du lieu bang trung gian @JoinTable
	   if(group.getUsers() != null){
	      group.getUsers().clear();
	      for(UserDTO userDTO : groupDTO.getUsers()) {
		      User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
		      group.getUsers().add(user);
	      }   
       }else {//them moi
    	   List<User> users = new ArrayList<>();
    	   
    	   for(UserDTO userDTO : groupDTO.getUsers()) {
    		   User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
    		   users.add(user);
    	   }	   
    	   group.setUsers(users);
    	   
    	   groupRepo.save(group);
       }
		
	}
}
