package com.login.demo.service;

import com.login.demo.oauth2.SessionUser;
import com.login.demo.repo.UserRepo;
import com.login.demo.vo.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private  final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;


    //회원 가입
    @Transactional
    public int googleJoin(String userID,String pw){
        System.out.println("이메일 :"+userID);
        Optional<UserEntity> user = userRepo.findByEmail(userID);
        System.out.println("요청 유저 "+user);
        if(pw != null){
            UserEntity vo = user.get();
            vo.setPassword(pw);
            System.out.println("비밀번호 "+pw+"로 변경 완료");
            return 1;
        }
        return 0;
    }

    //로그인
    public int login(String userID, String pw, HttpSession session){
//        String encodePw = encoder.encode(pw);
        Optional<UserEntity> user = userRepo.findByEmail(userID);
        System.out.println("입력 비밀번호 :"+pw);
        System.out.println("데이터베이스 비밀번호 :"+user.get().getPassword());
        if(encoder.matches(pw, user.get().getPassword())){
            session.setAttribute("user", new SessionUser(user.get()));
            session.setAttribute("id", user.get().getId());
            return 1;
        }
        return 0;
    }
    //비밀번호 재설정 여부 검사
    public int registPW(HttpSession session){
        //userID가 존재하면 구글로그인은 완료
        String user = (String)session.getAttribute("userID");
        //비밀번호를 설정하지 않은 상태면 emptypw = yes
        String pw = (String)session.getAttribute("emptypw");
        return user != null && pw != null && pw.equals("yes") ? 1 : 0;
    }

}
