package com.login.demo.repo.querydsl;

import com.login.demo.vo.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepoCustom {
    int findByMax();
//    @Query("select u from User u where u.email like '%@gmail.com'")
//    List<UserEntity> findUsersWithGmailAddress();
}
