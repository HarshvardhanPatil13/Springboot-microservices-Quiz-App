package com.HP.quiz_service.feign;

import com.HP.quiz_service.model.QuestionWrapper;
import com.HP.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> generateQuesForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);


    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);


    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
