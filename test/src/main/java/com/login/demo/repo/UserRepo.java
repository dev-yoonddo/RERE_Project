package com.login.demo.repo;

import com.login.demo.repo.querydsl.UserRepoCustom;
import com.login.demo.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserVO, String>, UserRepoCustom {
    Optional<UserVO> findByEmail(String email);
}
