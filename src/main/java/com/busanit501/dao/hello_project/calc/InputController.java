package com.busanit501.dao.hello_project.calc;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "inputController", urlPatterns = "/calc/input")
public class InputController extends HttpServlet {
    // 화면을 제공하는 목적의 컨트롤러... doGet 이용하기.
    /*
    * 파라미터:
    HttpServletRequest req: 클라이언트 요청 정보를 담고 있는 객체
    HttpServletResponse resp: 클라이언트에게 응답을 보내기 위한 객체
    예외:
    ServletException: 서블릿 처리 중 발생할 수 있는 예외
    IOException: 입출력 처리 중 발생할 수 있는 예외
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("inputController.. doGet 처리하기, 화면제공용");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/calc/input.jsp");
        rd.forward(req, resp);

    }

//RequestDispatcher
//설명: 서블릿에서 다른 리소스(JSP, 서블릿 등)로 요청을 전달하는 인터페이스
//메서드:
//getRequestDispatcher(String path): 지정된 경로의 리소스에 대한 RequestDispatcher 반환
//forward(request, response): 현재 요청과 응답 객체를 지정된 리소스로 전달
//forward() 메서드
//설명: 현재 요청 처리를 다른 리소스(JSP)로 넘기는 메서드
//특징:
//URL이 변경되지 않음 (브라우저는 변화를 인지하지 못함)
//요청과 응답 객체가 그대로 전달됨
//요청 처리를 완전히 위임함
//대체 옵션: sendRedirect 메서드
//Java
//resp.sendRedirect("/calc/input.jsp");
//URL이 변경됨 (새로운 요청이 발생)
//요청과 응답 객체가 새로 생성됨
//데이터 공유를 위해 세션이나 쿠키 필요
//WEB-INF 디렉토리 설명
//특징: 외부에서 직접 접근 불가능한 디렉토리
//용도: 보안이 필요한 리소스(JSP, 클래스 파일, 설정 파일 등) 보관
//장점: URL을 통한 직접 접근 방지, 항상 컨트롤러를 통해 접근하도록 강제
}
