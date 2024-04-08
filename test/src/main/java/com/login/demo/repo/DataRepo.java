package com.login.demo.repo;

import com.login.demo.repo.querydsl.DataRepoCustom;
import com.login.demo.vo.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataRepo extends JpaRepository<DataEntity, Integer>, DataRepoCustom {

    @Modifying
    @Query(value = "alter table DataEntity d auto_increment=1;\n"
            + "set count = 0;\n"
            + "update d set d.dataid = count = :count+1);",
            nativeQuery = true)
    void reIndexDataId();

    List<DataEntity> findTagnameByTagid(int tagid);
}
