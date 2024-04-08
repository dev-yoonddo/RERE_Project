package com.login.demo.service;

import com.login.demo.dto.TagDTO;
import com.login.demo.repo.TagRepo;
import com.login.demo.repo.UserRepo;
import com.login.demo.vo.TagEntity;
import com.login.demo.vo.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {
    @Autowired
    private final TagRepo tagRepo;

    public TagEntity registTag(TagDTO dto, int userid){
        TagEntity result = tagRepo.save(new TagEntity(0,dto.getTag().toString(),userid));
        if(result.getTag() != null){
            return result;
        }
        return null;

    }
    public String updateTag(TagDTO dto, int userid){
        tagRepo.save(new TagEntity(dto.getTagid(),dto.getTag().toString(),userid));
        return "ok";
    }
    public TagEntity searchTag(int userid){
        return tagRepo.findTagByUserid(userid);
    }
    @Transactional
    public void truncateTag() {
        tagRepo.truncateTag();
    }
}
