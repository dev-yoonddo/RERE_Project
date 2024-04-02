package com.login.demo.controller;

import com.login.demo.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value={"/community"})
    public String community(Model model) {
        log.debug("컨트롤러의 community() 메소드");
        log.info("커뮤니티 접속 시작");
        System.out.println("커뮤니티 접속");
        return "community";
    }

    @RequestMapping(value={"/", "/user/community"})
    public String home(Model model, HttpSession session) {
        log.debug("컨트롤러의 home() 메소드");
//        System.out.println("사용자: "+session.getAttribute("userID"));
//        if(session.getAttribute("userID") != null){
//            model.addAttribute("userID",session.getAttribute("userID"));
//        }
        return "redirect:community";
    }
    @GetMapping(value={"/tgt_login"})
    public String tgtlogin(Model model) {
        log.debug("컨트롤러의 login() 메소드");
        return "login_page";
    }
    @RequestMapping(value={"/google-pw"})
    public String googlelogin(Model model, HttpSession session) {
        log.debug("컨트롤러의 googlelogin() 메소드");
        String pw = (String)session.getAttribute("emptypw");
        System.out.println("패스워드가 없나요?: "+pw);
        if(pw != null && pw.equals("yes")){
            model.addAttribute("emptypw","yes");
            System.out.println("기존 비밀번호 없음");
        }else{
            System.out.println("기존 비밀번호 존재");
        }
        return "join_google";
    }
    @RequestMapping(value={"/write"})
    public String write(Model model) {
        log.debug("컨트롤러의 write() 메소드");
        return "write";
    }
//    @GetMapping("/get")
//    public String Accessmain(Model model){
//        Map<String, Object> result = service.getMain();
//        String text = (String)result.get("시작2");
//        model.addAttribute("mainText",text);
//        return "main";
//    }

}