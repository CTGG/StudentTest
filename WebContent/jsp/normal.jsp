<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score page</title>
</head>
<body>
	<table>
		
	  <c:forEach items="${scores}" var="item">
	    <tr>
	      <td><c:out value="studentName: ${item.id}" /></td>
	      <td><c:out value="courseName: ${item.course}" /></td>
	      <td><c:out value="score: ${item.score}" /></td>
	    </tr>
	  </c:forEach>
	</table>
	<form name='f1' id='f1' action='/StudentTest/Login' method='post'>
	<table align='center' border='0'>
	<tr><td colspan='2' align='center'>
	<input type='submit' value='Logout'></td>
	</tr>
	</table>
	</form>
	<p>总人数:	  <%

     ServletContext sc = request.getServletContext();
     String totalCounter = (Integer)sc.getAttribute("totalCounter")+"";

%><%= totalCounter %></p>
			<p>已登录人数 :	 <%

     String onlineCounter = (Integer)sc.getAttribute("onlineCounter")+"";

%><%= onlineCounter %></p>
			<p>游客人数 :		<% String guestCounter = (Integer)sc.getAttribute("guestCounter")+"";%>	
			<%= guestCounter %>
			  </p>
			 

</body>
</html>