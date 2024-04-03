package com.login.demo.controller;

import com.login.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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