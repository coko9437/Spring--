package com.busanit501.hello_project.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    // 리스트 목록 화면 제공이 목적.

    @Override //부모 클래스인 HttpServlet의 doGet 메서드를 재정의
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("todoListController.doGet 호출, 목록화면 제공");
        // 빌드 패턴으로, 해당 객체에서 사용하는 메서드를 연속적으로 사용하는 디자인 패턴형식
        req.getRequestDispatcher("/WEB-INF/todo/todoList.jsp").forward(req, resp);
        // 아직 화면은 구현안했음.
    }

}
