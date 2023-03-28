package com.example.project2.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.StudentDTO;
import com.example.project2.entity.Student;
import com.example.project2.entity.User;
import com.example.project2.entity.UserRole;
import com.example.project2.repo.StudentRepo;
import com.example.project2.repo.UserRepo;

@Service
public class StudentService {
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	UserRepo userRepo;

	@Transactional
	@CacheEvict(cacheNames = "students", allEntries = true)
	public void create(StudentDTO studentDTO) {
//  check user if role = ROLE_STUDENT
		User user = userRepo.findById(studentDTO.getId()).orElseThrow(NoResultException::new);
		for (UserRole userRole : user.getUserRoles()) {
			if (userRole.getRole().equals("ROLE_STUDENT"));
			Student student = new Student();
			student.setStudentCode(studentDTO.getStudentCode());
			student.setId(studentDTO.getId());
			studentRepo.save(student);
		}
	}
	
	@Transactional
	//@CacheEvict(cacheNames = "students", allEntries = true)
	@Caching(evict = { @CacheEvict(cacheNames = "students", allEntries = true), @CacheEvict(cacheNames = "users", allEntries = true) } )
	public void update(StudentDTO studentDTO) {
		Student student = studentRepo.findById(studentDTO.getId()).orElseThrow(NoResultException::new);
		student.setStudentCode(studentDTO.getStudentCode());
		studentRepo.save(student);
	}
	
	
	@Transactional
	@Cacheable(cacheNames ="students") //key - value
	public PageDTO<StudentDTO> search(String name, String studentCode, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> pageRS = null;
		if(StringUtils.hasText(name)) {
			pageRS = studentRepo.searchByName("%"+name+"%", pageable);
		}
		else if (StringUtils.hasText(studentCode)) {
		    pageRS  = studentRepo.searchByCode(studentCode,pageable);
		}
		else {
	   	    pageRS = studentRepo.findAll(pageable);
		}
		PageDTO<StudentDTO> pageDTO = new PageDTO<>();
		    pageDTO.setTotalPages(pageRS.getTotalPages());
		    pageDTO.setTotalElements(pageRS.getTotalElements());
		
		List<StudentDTO> list = new ArrayList<>();
		for(Student student : pageRS.getContent()) {
			list.add(new ModelMapper().map(student, StudentDTO.class));
		}
		pageDTO.setContents(list);
		 
		return pageDTO;
	}
}
