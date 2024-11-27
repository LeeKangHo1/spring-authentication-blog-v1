package com.metacoding.authblog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userPS = userRepository.findByUsername(loginDTO.getUsername());

        // 여긴 패스워드 검증. user 정보가 없는건 리포지토리에서 처리.
        // 선생님은 else보다 아래처럼 작성을 선호. else 부분 안 써도 되니까.
        // 클린코드 책에서는 비추천하는 방식이지만 어차피 회사 스타일에 맞춰야 한다.
        if (!userPS.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
            // 로그를 남겨서 서버에 패스워드 실패 카운팅 필요
        }
        return userPS;
    }
}
