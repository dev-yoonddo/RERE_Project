package com.login.demo.repo.querydsl;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepoCustom {
    void deleteTagdata(String tagname, int tagid);
    void deleteAllByTagid(int tagid);
}
