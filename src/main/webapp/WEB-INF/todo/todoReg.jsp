<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 23.
  Time: 오전 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>등록 임시 화면</h1>

<form action="/todo/register2" method="post">
    <input type="text" name="title" placeholder="todo의 제목을 입력 해주세요">
    <input type="date" name="dueDate">
    <button type="reset">리셋</button>
    <button type="submit">등록처리</button>
    <h3>이용한 서블릿 이름 : todoRegController</h3>
    <h3>해당 JSP파일 이름 : todoReg.jsp</h3>
</form>
</body>
</html>
