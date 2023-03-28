package com.example.project2.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project2.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE month(u.birthdate) = :m AND day(u.birthdate) = :d")
	List<User> findByBirthdate(@Param("m") int m,@Param("d") int d);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x")
	Page<User> searchByName(@Param("x")String x, Pageable pageable);
	
	@Query("SELECT u FROM User u" + " WHERE u.createdAt >= :start and u.createdAt <= :end ")
	Page<User> searchByDate(@Param("start") Date start, @Param("end") Date end, Pageable pageable);
	
	@Query("SELECT u FROM User u" + " WHERE u.createdAt >= :start")
	Page<User> searchByStartDate(@Param("start") Date start, Pageable pageable);
	
	@Query("SELECT u FROM User u" + " WHERE u.createdAt <= :end")
	Page<User> searchByEndDate(@Param("end") Date end, Pageable pageable);
	
	
	
  
}
