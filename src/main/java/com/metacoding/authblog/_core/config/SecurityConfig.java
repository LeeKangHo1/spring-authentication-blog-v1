package com.metacoding.authblog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 비밀번호 해시화 해주는 객체, 해시화된 비밀번호 비교는 스프링이 해야 하니까 IoC 컨테이너에 올려줘야 한다.
    @Bean
    public PasswordEncoder passwordEncoder() { // PasswordEncoder는 인코더들의 부모. 여러 인코더 집어넣을 수 있게
        return new BCryptPasswordEncoder();
    }

    // IoC 컨테이너에 이미 필터가 있다 -> 같은 타입의 필터를 등록할 경우 이전 필터을 덮어씌운다(싱글톤) -> 내가 설정한 조건으로 필터 적용
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // CSRF 보호 기능 비활성화
        http.csrf(c -> c.disable());

        // url pattern filter
        http.authorizeHttpRequests( r ->
                // s 는 security -> 인증이 필요한 기능은 url에 /s/를 넣는다.
                r.requestMatchers("/s/**").authenticated().anyRequest().permitAll())
                .formLogin(f ->
                        f.loginPage("/login-form") // 인증 필요 시 /login-form 경로로 GET 요청
                                .loginProcessingUrl("/login") // 로그인 POST 요청, 알고리즘 실행(loadUserByUsername 메서드)
                                .defaultSuccessUrl("/"));  // 로그인 완료되면 인덱스로

        return http.build();
    }
}
