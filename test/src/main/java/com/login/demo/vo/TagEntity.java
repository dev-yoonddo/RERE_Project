package com.login.demo.vo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@DynamicUpdate //변경 사항을 감지하고 자동 업데이트를 해주는 기능
@Table(name="tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagid;

    @Column(name = "tag")
    private String tag;

    @Column(name = "user_id")
    private int userid;

    @Builder
    public TagEntity(int tagid, String tag, int userid){
        this.tagid = tagid;
        this.tag = tag;
        this.userid = userid;
    }
}
