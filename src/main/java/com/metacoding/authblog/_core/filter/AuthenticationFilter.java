package com.metacoding.authblog._core.filter;

import com.metacoding.authblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    // import jakarta (톰캣 관련)
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 다운 캐스팅 필요
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            resp.sendRedirect("/login-form");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        System.out.println("작동하니?");
    }
}
