package com.metacoding.authblog.board;

import com.metacoding.authblog.user.User;
import com.metacoding.authblog.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user) {
        // @AuthenticationPrincipal 어노테이션이 있으면 시큐리티가 세션에서 user 꺼내서 준다.
        System.out.println("로그인 한 username: " + user.getUsername());
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }
}
