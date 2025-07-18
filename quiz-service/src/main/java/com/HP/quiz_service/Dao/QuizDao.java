package com.HP.quiz_service.Dao;

import com.HP.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao  extends JpaRepository<Quiz,Integer> {

}
