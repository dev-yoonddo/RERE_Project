package com.login.demo.oauth2;

import com.login.demo.vo.Role;
import com.login.demo.vo.UserVO;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String idAttributeKey;
    private String id;
    private String name;
    private String email;
    private String password;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String idAttributeKey, String id,
                           String name, String email
                            ,String password) {
        this.attributes = attributes;
        this.idAttributeKey= idAttributeKey;
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static OAuthAttributes of(String registrationId,
                                     String idAttributeID,
                                     Map<String, Object> attributes) {
        return ofGoogle(idAttributeID, attributes);
    }

    private static OAuthAttributes ofGoogle(String idAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .id((String) attributes.get("id"))
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .password((String) attributes.get("password"))
                .attributes(attributes)
                .idAttributeKey(idAttributeName)
                .build();
    }


    public UserVO toEntity() {
        return UserVO.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .role(Role.GUEST)
                .build();
    }
}
