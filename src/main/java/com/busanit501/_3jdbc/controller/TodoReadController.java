package com.busanit501._3jdbc.controller;

import com.busanit501._3jdbc.dto.TodoDTO;
import com.busanit501._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "todoReadController2", urlPatterns = "/tood/read2")
@Log4j2
public class TodoReadController extends HttpServlet {
    // 하나조회 컨트롤러
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
//            화면에서 쿼리스트링 형식으로 전달
                // ex) /todo/read2?tno=2
//            화면으로부터 전달 받은 tno 번호를 이용해서 서비스에 하나 조회를 요청하기.
            Long tno = Long.parseLong(req.getParameter("tno"));
//            req.getParameter("tno") -> 결과값이 문자열이라서. 숫자타입으로 변환

            // 서비스에 하나조회 요청하기.
            TodoDTO todoDTO = todoService.getByTno(tno);

            // 데이터를 화면에 탑재.
            req.setAttribute("dto",todoDTO);
            // 화면 전달.
            req.getRequestDispatcher("/WEB-INF/todo/todoRead2.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
                throw new RuntimeException(e);
        }

    }

}
