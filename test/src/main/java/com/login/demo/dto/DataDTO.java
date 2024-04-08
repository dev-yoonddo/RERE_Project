package com.login.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
public class DataDTO {

    private int dataid;
    private int tagid;
    private List<String> taglist;

}
