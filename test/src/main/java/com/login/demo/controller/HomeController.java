package com.login.demo.controller;

import com.login.demo.oauth2.LoginUser;
import com.login.demo.oauth2.SessionUser;
import com.login.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@AllArgsConstructor

public class HomeController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value={"/","/community"})
    public String community(Model model) {
        log.info("커뮤니티 접속 시작");
        return "community";
    }

    @RequestMapping(value={"/access/{url}"})
    public String sessionUser(Model model,@PathVariable String url, @LoginUser SessionUser session) {
        log.info("로그인확인");
        if(session != null){
            log.info("로그인ok");
            log.info(session.getName());
            log.info(session.getEmail());
            return url;
        }
        return "redirect:/oauth2/authorization/google";
    }
    @RequestMapping(value={"/user"})
    public String rere(Model model) {
        log.info("접속 시작");
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