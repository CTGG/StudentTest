package servlets;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



 


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
		ServletContext Context= getServletContext();
		Context.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		
        // Logout action removes session
        if (null != session.getAttribute("id")) {
        	System.out.println("excuse me !");
        	ServletContext Context= getServletContext();
     		int onlineCounter= (int) Context.getAttribute("onlineCounter");
     		int guestCounter= (int) Context.getAttribute("guestCounter");
     		guestCounter+=1;
     		onlineCounter-=1;
     		Context.setAttribute("guestCounter", guestCounter);
     		Context.setAttribute("onlineCounter", onlineCounter);
            session.invalidate();
            session = null;
            
        }
       
        
       
		
        //show login page
        doGet(request, response);
        
	}

}
