package com.login.demo.service;

import com.login.demo.dto.QuestionDTO;
import com.login.demo.repo.QuestionRepo;
import com.login.demo.vo.QuestionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepo questionRepo;

    public QuestionEntity registQues(QuestionDTO dto){
        return questionRepo.save(new QuestionEntity(
                0,
                dto.getTitle(),
                dto.getContent(),
                dto.getDate(),
                dto.getUserid(),
                dto.getQuestag()));
    }

    public List<QuestionEntity> searchQues(String questag){
        return questionRepo.findAllByQuestag(questag);
    }
}
