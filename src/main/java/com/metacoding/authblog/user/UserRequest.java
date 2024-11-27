package com.metacoding.authblog.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


public class UserRequest {

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
