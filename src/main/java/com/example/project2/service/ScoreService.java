package com.example.project2.service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.dto.ScoreDTO;
import com.example.project2.entity.Course;
import com.example.project2.entity.Score;
import com.example.project2.entity.Student;
import com.example.project2.repo.CourseRepo;
import com.example.project2.repo.ScoreRepo;
import com.example.project2.repo.StudentRepo;

@Service
public class ScoreService {
	  @Autowired 
	  StudentRepo studentRepo;
	  @Autowired
	  CourseRepo courseRepo;
	  @Autowired
	  ScoreRepo scoreRepo;
	  
	  @Transactional
	  public void create(ScoreDTO scoreDTO) {
		  Student student = studentRepo.findById(scoreDTO.getStudent().getId()).orElseThrow(NoResultException::new);
		  Course course = courseRepo.findById(scoreDTO.getCourse().getId()).orElseThrow(NoResultException::new);
		  
		  Score score = new Score();
		  score.setScore(scoreDTO.getScore());
		  score.setStudent(student);
		  score.setCourse(course);
		  
		  scoreRepo.save(score);
		  
	  }
}
