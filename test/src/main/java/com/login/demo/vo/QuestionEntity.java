package com.login.demo.vo;

import com.login.demo.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@DynamicUpdate //변경 사항을 감지하고 자동 업데이트를 해주는 기능
@Table(name="question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ques_id")
    private int quesid;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private String date;

    @Column(name = "user_id")
    private  int userid;

    @Column(name = "ques_tag")
    private String questag;

    @Builder
    public QuestionEntity(int quesid, String title, String content,
                          String date, int userid, String questag){
        this.quesid = quesid;
        this.title = title;
        this.content = content;
        this.date = date;
        this.userid = userid;
        this.questag = questag;
    }

}
