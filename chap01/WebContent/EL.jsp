<%@page import="chap07.UserEL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chap01 - EL 测 试</title>
</head>
<body>
<%--EL 域 使用request --%>
	<%request.setAttribute("useru", "yuan") ;%>
	<%=request.getAttribute("useru") %>
	${requestScope.useru}
<br>
<%--EL 使用session --%>
	<%session.setAttribute("usery", "lei") ;%>
	<%=session.getAttribute("usery") %>
	${sessionScope.usery}
<br>
<%--EL 使用application --%>
	<%application.setAttribute("userl", "yuanlei") ;%>
	<%=application.getAttribute("userl") %>
	${userl}
<br>

<%--通过 java 类获取数据 --%>
<%UserEL user = new UserEL("yuanEL","leiEL") ;
	request.setAttribute("user",user);
%>
${user.pass}
</body>
</html>