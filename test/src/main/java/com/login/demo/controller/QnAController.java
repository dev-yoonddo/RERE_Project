package com.login.demo.controller;

import com.login.demo.dto.QuestionDTO;
import com.login.demo.service.DataService;
import com.login.demo.service.QuestionService;
import com.login.demo.service.TagService;
import com.login.demo.vo.DataEntity;
import com.login.demo.vo.QuestionEntity;
import com.login.demo.vo.TagEntity;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class QnAController {
    @Autowired
    private final QuestionService questionService;
    private TagService tagService;
    private final DataService dataService;
    private final Logger log = LoggerFactory.getLogger(QnAController.class);

    @PostMapping("/ques/regist")
    public String registQues(QuestionDTO dto, HttpSession session){
        log.info("질문 작성"+dto);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(session.getAttribute("id") != null) {
            int userid = (Integer)session.getAttribute("id");
            dto.setDate(date.format(formatter));
            dto.setUserid(userid);
            log.info("질문 작성"+dto);
            QuestionEntity result = questionService.registQues(dto);
            return result.getTitle();
        }
        return null;
    }
    @GetMapping("/ques/search")
    public List<QuestionEntity> searchQues(HttpSession session){
        int userid = 0;
        if(session.getAttribute("id")!= null) {
            userid = Integer.parseInt(String.valueOf(session.getAttribute("id")));
            //사용자가 설정한 태그 가져오기
            TagEntity tag = tagService.searchTag(userid);
            List<QuestionEntity> queslist = new ArrayList<>();
            //태그가 존재하면
            if(tag.getTag() != null){
                //가져온 태그 아이디로 데이터 검색
                // ex) tag 테이블 -> [spring, java, boot]
                //     data 테이블 -> tagid = 1, tagname = spring
                //                   tagid = 1, tagname = java
                List<DataEntity> data = dataService.searchData(tag.getTagid());
                //데이터와 일치하는 게시물을 리스트에 저장
                for(DataEntity j : data){
                    queslist.addAll(questionService.searchQues(j.getTagname()));
                }
            }

            System.out.println("사용자태그에 해당되는 질문리스트" + queslist);
            /*
            List<TagEntity> tags = tagService.searchTag(userid);
            for(TagEntity i : tags){
                String t = i.getTag();
                String[] ta = t.split(",");
                int index = 0;
                for (String j : ta) {
                    list.add(index, j);
                    index++;
                }
            }
            */

            return queslist;
        }
        return null;
    }
}
