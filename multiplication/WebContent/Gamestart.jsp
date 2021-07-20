<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="gugu" class="gugudan.Gugudan" scope="page"/>
<jsp:setProperty name="gugu" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan game start</title>
</head>
<body>
	<%
	String ques = gugu.Gugudan_make();
	PrintWriter script = response.getWriter();
	script.println(ques);
	%>
	<form action="Checkanswer.jsp">
		<label for = "answer"> answer : </label>
		<input type="text" id="answer" name="answer"><br><br>
		<input type="submit" value="Submit answer">
	</form>
</body>
</html>