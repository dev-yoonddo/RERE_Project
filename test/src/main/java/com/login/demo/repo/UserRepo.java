package com.login.demo.repo;

import com.login.demo.repo.querydsl.UserRepoCustom;
import com.login.demo.vo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, String>, UserRepoCustom {
    Optional<UserEntity> findByEmail(String email);
}
