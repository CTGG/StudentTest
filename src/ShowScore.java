

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class ShowScore
 */
@WebServlet("/ShowScore")
public class ShowScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowScore() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init() {
    	// TODO Auto-generated method stub
    	InitialContext jndiContext = null;
    	
    	Properties properties = new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext = new InitialContext(properties);
			dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/testscore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		boolean cookieFound = false;
		System.out.println(request.getParameter("login")+" log in");
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("LoginCookie")) {
					cookieFound = true;
					break;
				}
			}
		}
		
		if (session == null) {
			String loginValue = request.getParameter("login");
			boolean isLoginAction = (null == loginValue) ? false : true;
			if (isLoginAction) { // User is logging in
				if (cookieFound) { // If the cookie exists update the value only
					// if changed
					if (!loginValue.equals(cookie.getValue())) {
						cookie.setValue(loginValue);
						response.addCookie(cookie);
					}
				} else {
					// If the cookie does not exist, create it and set value
					cookie = new Cookie("LoginCookie", loginValue);
					cookie.setMaxAge(Integer.MAX_VALUE);
					System.out.println("Add cookie");
					response.addCookie(cookie);
				}

				// create a session to show that we are logged in
				session = request.getSession(true);
				session.setAttribute("login", loginValue);

				request.setAttribute("login", loginValue);
//				getStockList(request, response);
//				displayMyStocklistPage(request, response);
//				displayLogoutPage(request, response);

			} else {
				System.out.println(loginValue + " session null");
				// Display the login page. If the cookie exists, set login
//				response.sendRedirect(request.getContextPath() + "/Login");
			}
		} else {
			// 或未注销，重新加载该页面，session不为空
			String loginValue = (String) session.getAttribute("login");
			System.out.println(loginValue + " session");

			request.setAttribute("login", loginValue);
//			getStockList(request, response);
//			displayMyStocklistPage(request, response);
//			displayLogoutPage(request, response);

		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
