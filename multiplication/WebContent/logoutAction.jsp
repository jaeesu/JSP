<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>log out</title>
</head>
<body>
	<%
	session.invalidate();
	%>
	<script>
	alert("loged out");
	location.href = "index.jsp";
	
	</script>

</body>
</html>