package com.login.demo.service;


import com.login.demo.oauth2.OAuthAttributes;
import com.login.demo.oauth2.SessionUser;
import com.login.demo.repo.UserRepo;
import com.login.demo.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;

@Service
@RequiredArgsConstructor

public class Oauth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {
    private final UserRepo userRepo;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userIDAttributeID = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userIDAttributeID, oAuth2User.getAttributes());

        UserVO user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        httpSession.setAttribute("userID", user.getEmail());

        if(user.getPassword() == null || user.getPassword().equals("")){
            httpSession.setAttribute("emptypw","yes");
        }
        System.out.println("loadUser method");
        System.out.println(user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private UserVO saveOrUpdate(OAuthAttributes attributes) {
        UserVO user = userRepo.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return userRepo.save(user);
    }
}
