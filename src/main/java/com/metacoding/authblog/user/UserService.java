package com.metacoding.authblog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity(passwordEncoder));
    }

    // POST 요청
    // /login 호출됨
    // key 값이 정해져 있다 -> username, password
    // Content-Type -> x-www-form-urlencoded
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("loadUserByUsername 메서드 실행");

        User user = userRepository.findByUsername(username);
        return user;
        // 이 메서드 실행하면서 시큐리티가 passwordEncoder로 비밀번호 비교 알아서 하고 맞으면 세션에 username 등록
    }
}
