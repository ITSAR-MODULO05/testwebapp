<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>PAGINA DI LOGOUT</title>
<link rel="stylesheet" href="css/layout.css">
</head>
<body>
	<%String user = null;
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
		} else {
			user = (String) session.getAttribute("user");
		}
	%>
	<h3>
		Arrivederci	<%=user%>, logout.
	</h3>
	<br>
	<h3>Torna alla pagina di login</h3>
	<form action="logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>