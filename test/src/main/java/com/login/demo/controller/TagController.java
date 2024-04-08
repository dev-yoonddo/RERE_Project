package com.login.demo.controller;

import com.login.demo.dto.DataDTO;
import com.login.demo.dto.TagDTO;
import com.login.demo.service.DataService;
import com.login.demo.service.TagService;
import com.login.demo.vo.DataEntity;
import com.login.demo.vo.TagEntity;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor //생성자 자동 선언
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private final TagService tagService;
    private final DataService dataService;
    private final Logger log = LoggerFactory.getLogger(HomeController.class);



    //태그 저장하기
    @Transactional
    @GetMapping("/regist")
    public String registTag(@RequestParam List<String> tag, TagDTO dto, HttpSession session) throws Exception{
        System.out.println("저장태그"+ tag);

        int userid = 0;
        if(session.getAttribute("id") != null){
            userid = Integer.parseInt(String.valueOf(session.getAttribute("id")));
            dto.setTag(tag);
            TagEntity search = tagService.searchTag(userid);
            //사용자 태그가 존재하지 않으면
            if(search == null) {
                TagEntity result = tagService.registTag(dto, userid);
                if (result.getTag() != null) {
                    dataService.saveData(new DataDTO(0, result.getTagid(), tag));
                    return "success";
                }
                return "error";
            }else{
                System.out.println("이미 태그 존재");
                //이미 사용자 태그가 존재하면 업데이트
                String result = tagService.updateTag(new TagDTO(search.getTagid(), tag, userid), userid);
                if(result.equals("ok")){
                    dataService.updateData(new DataDTO(0,search.getTagid(), tag));

                    return "업데이트 성공";

                }
                return "업데이트 실패";
            }
        }
        return "null user";
    }
    //태그 수정하기
    @GetMapping("/update")
    public String updateTag(@RequestParam ArrayList<String> tag, TagDTO dto,HttpSession session){
        int userid = 0;
        if(session.getAttribute("id") != null) {
            userid = Integer.parseInt(String.valueOf(session.getAttribute("id")));
            TagEntity vo = tagService.searchTag(userid);
            if(vo.getTag() != null){
                dto.setUserid(userid);
                dto.setTagid(vo.getTagid());
                dto.setTag(tag);
                tagService.updateTag(dto,userid);
                return "update ok";
            }
            return "null tag";

        }
        return "null user";
    }
    @GetMapping("/search")
    public String searchTag(HttpSession session) throws NoSuchElementException {
        int userid = 0;
        if(session.getAttribute("id") != null)
        {
            userid = Integer.parseInt(String.valueOf(session.getAttribute("id")));
            TagEntity result = tagService.searchTag(userid);
            if(result == null){
                return null;
            }
            return result.getTag();
        }
        return null;
    }

}
