package com.login.demo.vo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@DynamicUpdate //변경 사항을 감지하고 자동 업데이트를 해주는 기능
@Table(name="user")
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = true)
    private Role role;

    @Builder
    public UserVO(int id, String name, String email, String password,Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public UserVO update(String name) {
        this.name = name;
        return this;
    }
    /*public String getRoleKey() {
        return this.role.getKey();
    }*/
   public String getRoleKey() {
       return this.role.getKey();
   }
}
