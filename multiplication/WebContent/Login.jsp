<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String)session.getAttribute("userID");
	}
	if(userID != null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('already loged in.')");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
	}
	%>
	<h1>test</h1>
	<form action="loginAction.jsp">
		<label for="userID">userID : </label>
		<input type="text" name="userID" placeholder="ID" maxlength="20"><br>
		<label for="userPassword">userPassword : </label>
		<input type="password" name="userPassword" placeholder="Pwd" maxlength="20"><br>
		<input type="submit" value="Login">
	</form>
	<button><a href = "Signup.jsp">회원가입</a></button>
</body>
</html>