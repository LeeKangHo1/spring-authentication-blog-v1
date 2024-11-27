package com.metacoding.authblog.board;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 인증을 체크해야 한다. (인가(권한)은 삭제할 때)
    @GetMapping("/board/save-form")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // 문제가 생기면 예외를 터트린다.
            throw new RuntimeException("인증되지 않음 401");
        }
        return "board/save-form";
    }
}
