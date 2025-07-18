package com.HP.quiz_service.service;


import com.HP.quiz_service.Dao.QuizDao;
import com.HP.quiz_service.feign.QuizInterface;
import com.HP.quiz_service.model.Question;
import com.HP.quiz_service.model.QuestionWrapper;
import com.HP.quiz_service.model.Quiz;
import com.HP.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questionIds=quizInterface.generateQuesForQuiz(category,numQ).getBody();

        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);

        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> ques = quizInterface.getQuestionsFromId(questionIds);

        return ques;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);

        return score;
    }
}
