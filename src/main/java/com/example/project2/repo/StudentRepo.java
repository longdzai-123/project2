package com.example.project2.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project2.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
    
	@Query("SELECT s FROM Student s JOIN s.user u WHERE u.name LIKE :x")
	Page<Student> searchByName(@Param("x") String s, Pageable pageable);
	
	// StudentCode = :code truyen vao 
    Student findByStudentCode(String code);
	
    @Query("SELECT s FROM Student s WHERE  s.studentCode = :x")
	Page<Student> searchByCode(@Param("x") String s, Pageable pageable);
}
