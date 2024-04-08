package com.login.demo.repo.querydsl.Impl;

import com.login.demo.controller.HomeController;
import com.login.demo.repo.querydsl.TagRepoCustom;
import com.login.demo.vo.QTagEntity;
import com.login.demo.vo.QUserEntity;
import com.login.demo.vo.TagEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor

public class TagRepoCustomImpl implements TagRepoCustom {
    @Autowired
    private final JPAQueryFactory queryFactory;
    private final QTagEntity tag = QTagEntity.tagEntity;
    private final Logger log = LoggerFactory.getLogger(TagRepoCustomImpl.class);

    @Override
    public TagEntity findTagByUserid(int userid){
        return queryFactory.select(tag)
                .from(tag).where(tag.userid.eq(userid)).fetchOne();
    }

}
