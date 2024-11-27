package com.metacoding.authblog._core.config;

import com.metacoding.authblog._core.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 안에 @Component 존재
public class FilterConfig {

    @Bean // 컴포넌트 스캔 시 @Bean이 있으면 메서드를 실행 -> 반환값을 IoC에 등록
    public FilterRegistrationBean addAuthFilter() {
        // 이 객체에 등록하면 스프링 내부에 등록 가능. 필터의 위치믄 톰캣 -> 톰캣필터 -> 스프링 필터 -> 디스패처 서블릿
        FilterRegistrationBean rg = new FilterRegistrationBean();
        rg.setFilter(new AuthenticationFilter());
        // 모든 주소에 동작
        rg.addUrlPatterns("/board/*");
        rg.addUrlPatterns("/user/*"); // 로그인 url을 user/login이 아니라 /login인 이유
        rg.setOrder(1); // 필터 순서 정해야 한다. 1번이 최우선

        return rg;
    }
}
