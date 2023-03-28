package com.example.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entity.Score;

public interface ScoreRepo extends JpaRepository<Score, Integer>{

}
