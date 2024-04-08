package com.login.demo.repo.querydsl.Impl;

import com.login.demo.controller.HomeController;
import com.login.demo.repo.querydsl.UserRepoCustom;
import com.login.demo.vo.QUserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {
    private final JPAQueryFactory queryFactory;
    private final QUserEntity user = QUserEntity.userEntity;
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public int findByMax(){
        log.debug("findByMax 메서드 실행");
        return queryFactory.select(user.id.max())
                .from(user)
                .fetchFirst();
    }

}
