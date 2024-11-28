package com.metacoding.authblog.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;


public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity(PasswordEncoder passwordEncoder) {
            // 비밀번호 해시화
            String encPassword = passwordEncoder.encode(password);
            System.out.println(encPassword);
            User user = new User(null, username, encPassword, email, null);
            return user;
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

}
