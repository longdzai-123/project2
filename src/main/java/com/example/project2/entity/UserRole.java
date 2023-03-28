package com.example.project2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String role;// ADMIN , MEMBER
    
    @ManyToOne // many userrole - to - one user
//  @JoinColumn(name = "user_id")
    private User user;
    
    
}
