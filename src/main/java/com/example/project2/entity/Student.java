
package com.example.project2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Data
@Entity
public class Student {
	@Id
    private Integer id;  // userid
    
	@Column(unique = true)
    private String studentCode;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
}
