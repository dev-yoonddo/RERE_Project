package com.login.demo.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
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

    @GetMapping(value={"/", "/user/community"})
    public String home(Model model) {
        log.debug("컨트롤러의 home() 메소드");
        return "redirect:/community";
    }
    @RequestMapping(value={"/login"})
    public String login(Model model) {
        log.debug("컨트롤러의 login() 메소드");
        return "login";
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