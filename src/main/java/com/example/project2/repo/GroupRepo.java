package com.example.project2.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project2.entity.Group;

public interface GroupRepo extends JpaRepository<Group, Integer> {
    @Query("SELECT g FROM Group g JOIN g.users u WHERE u.name LIKE :x ")
    Page<Group> searchByNameUser(@Param("x") String s,Pageable pageable);
}
