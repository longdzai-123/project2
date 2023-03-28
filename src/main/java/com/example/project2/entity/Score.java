package com.example.project2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Score {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   private double score; // diem thi monhoc/nguoi
   
   @ManyToOne
   private Student student;
   
   @ManyToOne
   private Course course;
   
}
