package com.login.demo.repo;

import com.login.demo.vo.AnswerEntity;
import com.login.demo.vo.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<AnswerEntity, Integer> {
}
