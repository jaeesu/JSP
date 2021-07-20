<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.io.PrintWriter" %>
<jsp:useBean id="gugu" class="gugudan.Gugudan" scope="page"/>
<jsp:setProperty name="gugu" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	PrintWriter script = response.getWriter();
	try{
		String answer = request.getParameter("answer");
		script.println(Integer.parseInt(answer));
		
		int val=0;
		val = gugu.check_result(Integer.parseInt(answer));
		if(val == 1){
			
			script.println("<script>");
			script.println("alert('정답입니다.')");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
		}else{
			script.println("<script>");
			script.println("alert('정답이 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	} catch (Exception e){
		
		e.printStackTrace();
		script.println("<script>");
		script.println("alert('잘못된 형식입니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}
	
	
	%>
</body>
</html>