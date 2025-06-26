package com.busanit501._3jdbc.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="logoutController", urlPatterns = "/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        log.info("LogoutController, doPost작업중.");

        // 로그아웃처리 -> 세션의 loginInfo 정보만 제거하면 자연스럽게 로그아웃됨.
        // 현재 임시 로그인 로직
        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate(); // = 리프레쉬

        resp.sendRedirect("/");

    }

}
