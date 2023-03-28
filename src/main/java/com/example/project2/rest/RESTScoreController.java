package com.example.project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.dto.ResponseDTO;
import com.example.project2.dto.ScoreDTO;
import com.example.project2.service.ScoreService;

@RestController
@RequestMapping("api/score")
public class RESTScoreController {
	   @Autowired
	   ScoreService scoreService;
	   @PostMapping("/")
	   public ResponseDTO<ScoreDTO> add(@RequestBody ScoreDTO scoreDTO){
		   scoreService.create(scoreDTO);
		   return ResponseDTO.<ScoreDTO>builder().status(200).data(scoreDTO).build();
	   }
}
