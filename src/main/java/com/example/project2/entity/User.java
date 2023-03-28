package com.example.project2.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
	@Column(name="avatar")
    private String avatar;//URL
    
	@Column(unique = true)
    private String username;
	
    private String password;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    
//    @Transient // field is not persistent.
//    private MultipartFile file;
    
    @CreatedDate // tu gen 
 //   @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date createdAt;
    
    
    
    //ko bat buoc 
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;
    
    
    
}
