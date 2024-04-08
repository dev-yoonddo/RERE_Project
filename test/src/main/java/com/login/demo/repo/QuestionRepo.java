package com.login.demo.repo;

import com.login.demo.vo.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findAllByQuestag(String questag);
}
