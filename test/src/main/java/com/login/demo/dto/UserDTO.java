package com.login.demo.dto;


import com.login.demo.vo.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;



@AllArgsConstructor
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;

}
