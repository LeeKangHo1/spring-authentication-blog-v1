package com.metacoding.authblog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    // 의존성 주입을 생성자로 해도 되고 autowired해도 되는데 final은 붙여라. 실수 방지
    private final UserService userService;
    private final HttpSession session;
    // request는 여러 개라 관리 불가. 싱글톤 객체만 가능

    @GetMapping("/login-form")
    public String loginForm() {
       return "user/login-form"; // user 폴더 안에 있으니까
    }

    @PostMapping("/login") // 비밀번호는 세션이나 url 쿼리스트링에 있으면 안되니까 예외로 Post로 전송
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        // 여기까지 작성했으면 username 없거나 패스워드가 틀리는 문제는 다른 레이어에서 다 처리된 상태

        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
        // 다 작성했으니 포스트맨으로 테스트
    }

    // 포스트맨으로 로그인 테스트
//    @ResponseBody
//    @GetMapping("/test/auth")
//    public String testAuth() {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        if (sessionUser == null) {
//            return "인증안됨";
//        }
//        return "인증됨 : " + sessionUser.getUsername();
//    }
}
