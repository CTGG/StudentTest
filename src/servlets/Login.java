package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



 


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

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		//check if session exists
		if (session != null) {
			//show score
			RequestDispatcher dispatcher =request.getRequestDispatcher("/ShowScore"); 
			if (dispatcher!= null) 
				dispatcher.include(request,response);
		}else{
			
			pw.println("   	<form name='f1' id='f1' action='/StudentTest/ShowScore' method='get'>"+
				      "<table align='center' border='0'>"+
				        "<tr>"+
				          "<td>Studentid</td>"+
				          "<td><input type='text' name='id'  size=25></td>"+
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
			ServletContext Context= getServletContext();
			int onlineCounter= (int) Context.getAttribute("onlineCounter");
			int guestCounter= (int) Context.getAttribute("guestCounter");
			int totalCounter = (int) Context.getAttribute("totalCounter");
			pw.println("<p>总人数:	 " + totalCounter + "</p>");
			pw.println("<p>已登录人数 :	" + onlineCounter + "</p>");
			pw.println("</p>游客人数 :		" + guestCounter + "</p>");
		}
    
       
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        // Logout action removes session
        if (null != session) {
            session.invalidate();
            session = null;
        }
       
        ServletContext Context= getServletContext();
		int onlineCounter= (int) Context.getAttribute("onlineCounter");
		int guestCounter= (int) Context.getAttribute("guestCounter");
		guestCounter+=1;
		onlineCounter-=1;
		Context.setAttribute("guestCounter", guestCounter);
		Context.setAttribute("onlineCounter", onlineCounter);
		
        //show login page
        doGet(request, response);
        
	}

}
