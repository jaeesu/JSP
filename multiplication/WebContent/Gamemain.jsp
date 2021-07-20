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
	if(userID == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('please log in')");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
	}
	%>
	<h1>test</h1>
	<form action="Gamestart.jsp">
		<fieldset>
			<legend> selection </legend>
			<input type="radio" id="easy" name="difficulty" value="easy">
			<label for = "easy"> easy </label>
			<input type="radio" id="hard" name="difficulty" value="hard">
			<label for = "hard"> hard </label>
		</fieldset>
		<br>
		<input type="submit" value="Game Start">
	</form>
</body>
</html>