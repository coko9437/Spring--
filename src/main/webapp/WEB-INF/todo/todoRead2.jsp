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
    <input type="text" name="tno" value="${dto.tno}" readonly>  <!--todoReadController에서 이렇게 정의함 (.setAttribute("dto",todoDTO));-->
  </div>                  <!--readonly : 읽기전용 임을 명시함. 사용자가 수정할 수 없음.-->

  <div>
    <input type="text" name="title" value="${dto.title}" readonly>
  </div>

  <div>
    <input type="date" name="dueDate" value="${dto.dueDate}">
  </div>

  <div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked":""} readonly>
  </div>                              <!-- finished가 true면 checked 출력-->

<div>
  <a href="/todo/update2?tno=${dto.tno}">수정폼</a>
  <a href="/todo/list2">목록가기</a>
</div>

<form id="form2" action="/todo/delete2" method="post">
  <input type="hidden" name="tno" value="${dto.tno}" readonly>
  <div>
    <button type="submit">삭제</button>
  </div>
</form>
</body>
</html>
