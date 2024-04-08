package com.login.demo.service;

import com.login.demo.dto.DataDTO;
import com.login.demo.dto.TagDTO;
import com.login.demo.repo.DataRepo;
import com.login.demo.vo.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataService {

    @Autowired
    private final DataRepo dataRepo;

    public String saveData(DataDTO dto){
        for(String i : dto.getTaglist()){
            dataRepo.save(new DataEntity(dto.getDataid(),dto.getTagid(), i));
        }
        return "data ok";
    }
    public List<DataEntity> searchData(int tagid){
        return dataRepo.findTagnameByTagid(tagid);
    }
    public String updateData(DataDTO dto){
        //이미 존재하는 태그를 저장할 리스트

        //태그 아이디에 해당되는 데이터 가져오기
        List<DataEntity> exist_tag = dataRepo.findTagnameByTagid(dto.getTagid());
        System.out.println("기존 태그 데이터" + exist_tag);
        dataRepo.deleteAllByTagid(dto.getTagid());

        for(String i : dto.getTaglist()){
            dataRepo.save(new DataEntity(0,dto.getTagid(),i));
        }

        dataRepo.reIndexDataId();

        System.out.println("새 태그" + dto.getTaglist());
        return "ok";
    }

}
