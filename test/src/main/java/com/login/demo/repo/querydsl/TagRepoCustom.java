package com.login.demo.repo.querydsl;

import com.login.demo.vo.TagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepoCustom {
    TagEntity findTagByUserid(int userid);
}
