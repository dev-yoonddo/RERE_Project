package com.login.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능

public class QuestionDTO {

    private int quesid;
    private String title;
    private String content;
    private String date;
    private int userid;
    private String questag;

}
