<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 20.
  Time: 오전 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 변경 전, POST 형식으로 화면에 직접 접근했음.--%>
<%--<form action ="calcResult.jsp" method="post">--%>
<%-- ㄴ   action : 폼에서 입력한 데이터를 전달 할 곳 (=도착지)--%>
<%--      method 전달방식을 post => 데이터를 body에 담아서 보냄.--%>
<%--      get과 비교하자면 공개되지 않는 데이터임.--%>

<%-- 변경 후, POST형식으로 접근하지만, 서블릿을 통해서 컨트롤러의 안내를 받아서 접근--%>
<form action="/calc/makeResult" method="post">
          <%--    /calc/makeResult 여기 경로를 담당하는 서블릿을 만들기.--%>
    <input type="number" name="num1">
    <input type="number" name="num2">
    <button type="submit">SEND</button>
    <h2>서블릿이용해서 접속함 : http://localhost:8080/calc/input</h2>
</form>
</body>
</html>
