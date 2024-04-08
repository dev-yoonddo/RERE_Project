package com.login.demo.repo;

import com.login.demo.repo.querydsl.TagRepoCustom;
import com.login.demo.vo.TagEntity;
import com.login.demo.vo.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepo extends JpaRepository<TagEntity, Integer>, TagRepoCustom {
    @Modifying
    @Query(
            value = "truncate table tag",
            nativeQuery = true
    )
    void truncateTag();
}
