<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="session" uri="../WEB-INF/tld/checkSession.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>

  	<form name='f1' id='f1' action='/StudentTest/ShowScore' method='get'>
				      <table align='center' border='0'>
				        <tr>
				          <td>Studentid</td>
				          <td><input type='text' name='id'  size=25></td>
				        </tr>
				        <tr>
				          <td>Password</td>
				          <td><input type='password' name='password' size=25></td>
				        </tr>
				       
				        <tr>
				          <td colspan='2' align='center'>
				          <input type='submit' value='Submit'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          <input type='reset' value='Logout'>
				          </td>
				        </tr>
				      </table>
				    </form>
				     <session:checkSession/>
			
			  
			  <%
	RequestDispatcher rd = request.getRequestDispatcher("../ShowScore");
	if(rd != null){
		rd.include(request, response);
	}
	%> 
	
</body>
</html>