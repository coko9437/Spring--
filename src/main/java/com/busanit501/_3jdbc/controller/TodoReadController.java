package com.busanit501._3jdbc.controller;

import com.busanit501._3jdbc.dto.TodoDTO;
import com.busanit501._3jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "todoReadController2", urlPatterns = "/todo/read2")
@Log4j2
public class TodoReadController extends HttpServlet {
    // 하나조회 컨트롤러
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
//            화면에서 쿼리스트링 형식으로 전달
            // ex) /todo/read2?tno=2
//            화면으로부터 전달 받은 tno 번호를 이용해서 서비스에 하나 조회를 요청하기.
            Long tno = Long.parseLong(req.getParameter("tno"));
//            req.getParameter("tno") -> 결과값이 문자열이라서. 숫자타입으로 변환

            // 서비스에 하나조회 요청하기.
            TodoDTO todoDTO = todoService.getByTno(tno);

            // 데이터를 화면에 탑재.
            req.setAttribute("dto", todoDTO);

//==================================================================
            // 서버 -> 웹브라우저에게... 쿠키를 이렇게 저장해주세요 하고 응답하는
            // 사용자가 하나 조회를 한 번호를 쿠키에 담을 예정
            // 직업개요.
//            1. 쿠키박스의 이름 임의로... : viewTodos
//            2. 쿠키박스에 viewTodos 없으면 새로만들고, 있으면 기록하기
//            3. 쿠키박스 존재여부 확인할 기능필요... findCookie

            // 쿠키찾기 req.getCookies() : 전체 쿠키목록
            // 있으면 있는쿠키를 사용 | 없으면 새로생성해서 사용.
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");

            // 쿠키 박스에 저장된 사용자 -> 조회한 todo의 번호를 문자열 혀앹로 저장 할 예정.
            // 저장이 되어있다면? 불러오자.
            String todoListStr = viewTodoCookie.getValue();
            // 상태변수... exist
            boolean exist = false;
            // 유효성 체크 todoListStr -> 문자열이 NULL인지 여부, tno(숫자) + "-", 3-4-, => 이런형식으로 검사
            // todoListStr = "1-2-6-3-...." =>> 조회한 todo번호를 -구분자로해서 포맷으로 저장.
            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                exist = true;
            }
            log.info("쿠키박스 존재 + 안에 조회한 내용이 있다면 => exist 트루" + exist);

            // 쿠키박스안에 조회한 내용이 없는경우 -> 기록하자.
            if (!exist) { // !true => 부정true => False 즉 내용이 없는경우
                // 문자열 안에 조회한 번호를 기록
                todoListStr += tno + "-";
//                쿠키에 설정하는 옵션
                viewTodoCookie.setValue(todoListStr);
//                쿠키를 적용할 범위... 전체경로("/")
                viewTodoCookie.setPath("/");
//                쿠키 생존시간 : 5분
                viewTodoCookie.setMaxAge(60 * 5);
//                서버 에서 웹브라우저로 전달
                resp.addCookie(viewTodoCookie);
            }
//==================================================================

            // 화면 전달.
            req.getRequestDispatcher("/WEB-INF/todo/todoRead2.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    // 쿠키찾기기능
    // 매개변수 1) 쿠키상자들이 들어있는 전체목록
    // 매개변수 2) 쿠키 상자의 이름 : viewTodos
    private Cookie findCookie(Cookie[] cookies, String findCookieName) {
        //1. 찾을 쿠키를 담아둘 임시 쿠키 정의
        Cookie targetCookie = null;

        //2. 전체목록에서 반복문으로 쿠키박스의 이름이 'viewTodos' 해당하는게 있는지 조사
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(findCookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }//end if
        // 3. 찾고자하는 쿠키박스가 없다면 생성하기.
        if (targetCookie == null) {
            targetCookie = new Cookie(findCookieName, "");
            targetCookie.setPath("/"); // 사용될 수 있는 경로를 전체로
//            쿠키 유효시간? 하루 -> 테스트 목적으로 5분으로 줄이자.
//            targetCookie.setMaxAge(60 * 60 * 24); //60초 60분 24시간 = 하루
            targetCookie.setMaxAge(60 * 5);

        }


        return targetCookie;
    }
}