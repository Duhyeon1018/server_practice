<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 12. 3.
  Time: 오전 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>ex4, 서버로부터 넘어온 더미 데이터 확인 </h1>
<%--<h2>넘어온 데이터 msg : ${msg}</h2>--%>
<h2>넘어온 데이터 msg : <c:out value="${msg}"></c:out> </h2>

</body>
</html>