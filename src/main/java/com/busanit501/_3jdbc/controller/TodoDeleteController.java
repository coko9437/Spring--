package com.busanit501._3jdbc.controller;

import com.busanit501._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoDeleteController2", urlPatterns = "/todo/delete2")
@Log4j2
public class TodoDeleteController extends HttpServlet {
//    삭제 로직이라 Post로 처리.
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 삭제할 번호를 가져오기, ( 하나조회 하는 방식과 동일함.)
        Long tno = Long.parseLong(req.getParameter("tno"));
        log.info("TodoDeleteController에서 넘어온 tno 번호 확인" +tno);

        try{
            //실제 삭제처리
            todoService.remove(tno);
        } catch (Exception e) {
            log.error(e.getMessage());
                throw new RuntimeException(e);
        }
        //리다이렉트
        resp.sendRedirect("/todo/list2");

    }
}
