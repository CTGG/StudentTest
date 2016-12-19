package servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;



 


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletContext Context= getServletContext();
//		int webCounter= Integer.parseInt((String) Context.getAttribute("webCounter"));
//		if (null == request.getParameter("Logout")) {
//			System.out.println("pageCounter++\n");
//			webCounter++;
//			Context.setAttribute("webCounter", Integer.toString(webCounter));
//		}
		PrintWriter pw = response.getWriter();		
		String login="";
		HttpSession session = request.getSession(true);
		Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
  
        Integer ival = new Integer(1);
        		
        if (null != cookies) {
            // Look through all the cookies and see if the
            // cookie with the login info is there.
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("LoginCookie")) {
                    login=cookie.getValue();
                    break;
                }
            }
        }
    
        // Logout action removes session, but the cookie remains
        if (null != request.getParameter("Logout")) {
            if (null != session) {
            	session.invalidate();
                session = null;
            }
        }
        
        
        pw.println("   	<form name='f1' id='f1' action='/StudentTest/ShowScore' method='post'>"+
      "<table align='center' border='0'>"+
        "<tr>"+
          "<td>Studentid</td>"+
          "<td><input type='text' name='id' value='"+login+"' size=25></td>"+
        "</tr>"+
        "<tr>"+
          "<td>Password</td>"+
          "<td><input type='password' name='password' size=25></td>"+
        "</tr>"+ 
       
        "<tr>"+
          "<td colspan='2' align='center'>"+
          "<input type='submit' value='Submit'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
          "<input type='reset' value='Logout'>"+
          "</td>"+
        "</tr>"+
      "</table>"+
    "</form>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
