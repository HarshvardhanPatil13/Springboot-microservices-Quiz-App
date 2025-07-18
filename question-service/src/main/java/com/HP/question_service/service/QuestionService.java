package com.HP.question_service.service;

import com.HP.question_service.Dao.QuestionDao;
import com.HP.question_service.model.Question;
import com.HP.question_service.model.QuestionWrapper;
import com.HP.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>( questionDao.findAll(), HttpStatus.OK);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED );


    }

    public ResponseEntity<List<Integer>> generateQuesForQuiz(String categoryName, Integer numQuestions) {

        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questionIds,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        //List<Question> questions = new ArrayList<>();
        for(Integer id : questionIds)
        {
            Question q = questionDao.findById(id).get();
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            qw.setId(q.getId());
//            qw.setQuestion_title(q.getQuestion_title());
//            qw.setOption1(q.getOption1());
//            qw.setOption2(q.getOption2());
//            qw.setOption3(q.getOption3());
//            qw.setOption4(q.getOption4());
            wrappers.add(qw);

        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right=0;
        for(Response res : responses)
        {
            Question question = questionDao.findById(res.getId()).get();
            if(res.getResponse().equals(question.getRight_answer()))
            {
                right++;
            }
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
