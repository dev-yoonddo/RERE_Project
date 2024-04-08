package com.login.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
public class TagDTO {


    private int tagid;
    private List<String> tag;
    private int userid;

}
