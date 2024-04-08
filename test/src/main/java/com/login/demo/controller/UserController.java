package com.login.demo.controller;

import com.login.demo.oauth2.SessionUser;
import com.login.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    //구글 로그인 후 접속되는 주소
    @RequestMapping(value={"/google-login"})
    public String googlelogin(Model model, HttpSession session) {
        log.debug("컨트롤러의 googlelogin() 메소드");
        //사용자를 구별하기 위해 3가지로 분류
        //1. 회원가입(비밀번호재설정) 및 로그인이 모두 완료된 사용자
        //2. 회원가입은 완료, 로그인은 미완료 된 사용자
        //3. 회원가입, 로그인 모두 미완료 된 사용자

        // 회원가입,로그인 모두 완료된 사용자 : session에 user,userID값이 존재하고 emptypw 값은 존재하지 않는다.
        if (session.getAttribute("user") != null && userService.registPW(session) == 0) {
            System.out.println("이미 회원가입 및 로그인이 된 사용자");
            return "redirect:/";
        }
        //회원가입,비밀번호 재설정은 완료됐지만 로그인은 하지 않은 사용자
        else if (session.getAttribute("user") == null) {
            System.out.println("기존 비밀번호 존재");
            return "join_google";
        }
        //비밀번호 재설정, 로그인이 모두 되지 않은 사용자
        //emptypw값을 전달해 화면에 비밀번호 설정이 표시되도록 한다.
        else {
            model.addAttribute("emptypw", "yes");
            System.out.println("기존 비밀번호 없음");
            return "join_google";
        }
    }

    //비밀번호 재설정
    @PostMapping("/update-pw")
    public String updatePassword(@RequestParam String userPassword, Model model, HttpSession session){
        log.info("password 변경 시도");
        String userID = (String)session.getAttribute("userID");
        String pw = userPassword;
        String encodePw = encoder.encode(pw);
        log.debug("변경 전 패스워드: "+ pw);
        log.debug("변경 후 패스워드: "+ encodePw);
        if(pw != null) {
            userService.googleJoin(userID, encodePw);
            //비밀번호 변경이 완료되면 emptypw값 삭제
            session.removeAttribute("emptypw");
            log.info("패스워드 변경 성공");
        }else {
            log.info("패스워드 변경 실패 ㅠㅠ");
        }
        //비밀번호 변경이 완료되면 초기 로그인페이지로 이동 -> 자동으로 비밀번호가 정상적으로 변경됐는지 검사
        return "redirect:/google-login";
    }

    @PostMapping("/login-pw")
    public String login(@RequestParam String userPassword, HttpSession session, Model model){
        String userID = (String)session.getAttribute("userID");
        if(userService.login(userID, userPassword, session) == 1){
            log.info("로그인 성공!!");
            return "redirect:community";
        }
        log.info("로그인 실패ㅠㅠ");
        //비밀번호 변경이 완료되면 초기 로그인페이지로 이동
        return "redirect:/google-login";
    }

}
