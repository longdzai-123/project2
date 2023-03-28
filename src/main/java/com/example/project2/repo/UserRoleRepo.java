package com.example.project2.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project2.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{
	
	@Modifying
	@Query("DELETE FROM UserRole ur WHERE ur.user.id = :uid")
    public void delteByUserId(@Param("uid") int userId);
	
	@Query("SELECT ur FROM UserRole ur WHERE ur.user.id = :uid")
	Page<UserRole> SearchByUserId(@Param("uid") int userId, Pageable pageable);
	
	@Query("SELECT ur FROM UserRole ur WHERE ur.role LIKE :role")
	Page<UserRole> SearchByRole(@Param("role") String role, Pageable pageable);
}
