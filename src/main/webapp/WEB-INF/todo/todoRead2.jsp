<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 23.
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>하나 조회 화면</h1>
  <div>
    <input type="text" name="tno" value="${dto.tno}">  <!--req.setAttribute("dto",todoDTO);-->
  </div>
  <div>
    <input type="text" name="title" value="${dto.tno}">
  </div>
  <div>
    <input type="date" name="dueDate" value="${dto.dueDate}" readonly>
  </div>
  <div>
    <input type="checkbox" name="finished" value="${dto.finished ? "checked":""}" readonly>
  </div>
  <div>
    <a href="/todo/update?tno=${dto.tno}">수정폼</a>
    <a href="/todo/delete2">삭제</a>
    <a href="/todo/list2">목록가기</a>
  </div>
</body>
</html>
