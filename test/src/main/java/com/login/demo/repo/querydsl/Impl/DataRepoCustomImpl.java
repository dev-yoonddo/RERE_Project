package com.login.demo.repo.querydsl.Impl;

import com.login.demo.repo.querydsl.DataRepoCustom;
import com.login.demo.vo.DataEntity;
import com.login.demo.vo.QDataEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class DataRepoCustomImpl implements DataRepoCustom {
    @Autowired
    private final JPAQueryFactory queryFactory;
    private final QDataEntity data = QDataEntity.dataEntity;
    private final Logger log = LoggerFactory.getLogger(DataRepoCustomImpl.class);


    @Override
    public void deleteTagdata(String tagname, int tagid){
        queryFactory.delete(data).where(data.tagname.eq(tagname).and(data.tagid.eq(tagid))).execute();
    }
    @Override
    public void deleteAllByTagid(int tagid){
        queryFactory.delete(data).where(data.tagid.eq(tagid)).execute();
    }
 
}
