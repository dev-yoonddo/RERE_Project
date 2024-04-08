package com.login.demo.vo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity//entity = 데이터베이스와 직접 매핑하는 클래스, dto = 객체를 전달하는 클래스
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@DynamicUpdate //변경 사항을 감지하고 자동 업데이트를 해주는 기능
@Table(name="data", indexes = @Index(name = "data_id", columnList = "tag_name", unique = true))
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private int dataid;

    @Column(name = "tag_id")
    private int tagid;

    @Column(name = "tag_name")
    private String tagname;


    @Builder
    public DataEntity(int dataid, int tagid, String tagname){
        this.dataid = dataid;
        this.tagid = tagid;
        this.tagname = tagname;
    }
}
