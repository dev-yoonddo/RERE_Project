package com.login.demo.vo;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@Table(name="user")
public class UserVO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public UserVO(String id, String name, String email, String password,Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public UserVO update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }
    /*public String getRoleKey() {
        return this.role.getKey();
    }*/
   public String getRoleKey() {
       return this.role.getKey();
   }
}
