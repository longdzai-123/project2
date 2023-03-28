package com.example.project2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data 
@Entity
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY )
   private Integer id;
   
   private String name;
}
