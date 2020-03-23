<%@ page language="java" contentType="text/html; charset=US-ASCII"    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>PAGINA DI LOGIN EFFETTUATA</title>
<link rel="stylesheet" href="css/layout.css">
</head>
<body>
<%
//permette l'accesso solo se esiste la sessione
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
}else {
	user = (String) session.getAttribute("user");
}
%>
<h3>Benvenuto <%=user %>, Login effettuata con successo.</h3>
<hr>
<div>
<a href="Logout.jsp">Logout</a>
</div>
</body>
</html>