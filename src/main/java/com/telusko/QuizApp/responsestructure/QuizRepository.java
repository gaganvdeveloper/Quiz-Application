package com.telusko.QuizApp.responsestructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.QuizApp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
