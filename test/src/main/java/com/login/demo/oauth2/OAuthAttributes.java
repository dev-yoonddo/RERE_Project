package com.login.demo.oauth2;

import com.login.demo.vo.Role;
import com.login.demo.vo.UserVO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String password;
    private Role role;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email,
                           String password,
                           Role role){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static OAuthAttributes of(String registrationId,
                                     String nameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(nameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String nameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .password(null)
                .attributes(attributes)
                .nameAttributeKey(nameAttributeName)
                .build();
    }


    public UserVO toEntity() {
        return UserVO.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
