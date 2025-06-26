package com.busanit501._3jdbc.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/todo/*") // /todo/*해당 경로로 요청되는 request는 모두 검사 대상자.
@Log4j2
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginCheckFilter 에서 작업중. | /todo/* |로 접근시 검사 대상자.");
        // 로그인 체크 규칙,
//            ServletRequest -> HttpServletRequest로 다운캐스팅
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        // 세션 이용하는 도구 가져오기
        HttpSession session = req.getSession();

        // 세션에 키(loginInfo), 값(mid+mpw) 존새하면 => 로그인된상태
        // 세션에 "" 없으면 => 로그인 안되어있음.
        if(session.getAttribute("loginInfo")==null){
            log.info("LoginCheckFilter 에서 작업중. loginInfo 비어있음.");
            resp.sendRedirect("/login");
            return;
        }

        // 위의 검사 규칙이 끝나고 이어서 진행하겠다. 라는 의미.
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
