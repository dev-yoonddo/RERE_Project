package com.login.demo.oauth2;

import com.login.demo.vo.UserVO;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String name;

    public SessionUser(UserVO user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
