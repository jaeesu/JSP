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
	<h1>signup</h1>
	<form action="signupAction.jsp">
		<label for="userID">ID : </label>
		<input type="text" name="userID" placeholder="ID" maxlength="20"><br>
		
		<label for="userPassword">Password : </label>
		<input type="password" name="userPassword" placeholder="Pwd" maxlength="20"><br>
		
		<label for="userName">Name : </label>
		<input type="text" name="userName" placeholder="name" maxlength="20"><br>
		
		<label for="userGender">Gender : </label>
		<input type="radio" id="male" name="userGender" value="male" checked>
		<label for = "male"> male </label>
		<input type="radio" id="female" name="userGender" value="female">
		<label for = "female"> female </label><br><br>
		
		
		<label for="usermail">Email : </label>
		<!-- 어떻게 처리??? -->
		<input type="text" id="usermail" name="userEmail">
		<!-- 
		<select id = "email" name="userEmail2">
			<option value="">----select----</option>
			<option value="naver.com">naver.com</option>
			<option value="google.com">google.com</option>
		</select><br><br> -->
		
		<input type="submit" value="Signup">
	</form>
</body>
</html>