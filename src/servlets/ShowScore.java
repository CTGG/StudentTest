package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Score;


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
    }
    
    
    public void init() {
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
		ServletContext Context= getServletContext();
		int guestCounter= (int) Context.getAttribute("guestCounter");
		int onlineCounter= (int) Context.getAttribute("onlineCounter");
		HttpSession session = request.getSession(false);
		if (session == null) {
			guestCounter-=1;
			onlineCounter+=1;
			Context.setAttribute("guestCounter", guestCounter);
			Context.setAttribute("onlineCounter", onlineCounter);
		}
		
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		
		System.out.println(request.getParameter("id")+" log in");
		if (session == null) {
			String loginValue = request.getParameter("id");
			// create a session to show that we are logged in
			session = request.getSession(true);
			session.setAttribute("id", loginValue);
		}
		
		displayScores(request, response);
	}


	private void displayScores(HttpServletRequest request, HttpServletResponse response) {
		boolean isExist = isExist(request.getParameter("id"));
		if (isExist) {
			ArrayList<Score> list = new ArrayList<>();
			ResultSet resultSet = null;
			try {
				Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("select id, course,score from score where id = ?");
				statement.setString(1, request.getParameter("id"));
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					Score score = new Score();
					score.setId(resultSet.getString("id"));
					score.setCourse(resultSet.getString("course"));
					score.setScore(resultSet.getInt("score"));
					list.add(score);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (resultSet == null) {
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println("hhh");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head><title>"+request.getParameter("id")+"</title></head>");
				out.println("<body>");
				for (int i = 0; i < list.size(); i++) {
					Score score = (Score) list.get(i);
					
					out.println("<p> studentName:  "+score.getId()+"</p>");
					out.println("<p> courseName:   "+score.getCourse()+"</p>");
					String sc = "<p";
					
					if (score.getScore() == -1) {
						sc+=" style='color:red'>";
						sc+="score:        not take examination";
					}else {
						sc+=">score:       ";
						sc+=score.getScore();
					}
					sc+="</p>";
					out.println(sc);
				}
				ServletContext Context= getServletContext();
				int onlineCounter= (int) Context.getAttribute("onlineCounter");
				int guestCounter= (int) Context.getAttribute("guestCounter");
				int totalCounter = (int) Context.getAttribute("totalCounter");
				out.println("<p>总人数:	 " + totalCounter + "</p>");
				out.println("<p>已登录人数 :	" + onlineCounter + "</p>");
				out.println("</p>游客人数 :		" + guestCounter + "</p>");
				out.println("<form name='f1' id='f1' action='/StudentTest/Login' method='post'><table align='center' border='0'><tr><td colspan='2' align='center'><input type='submit' value='Logout'></td></tr></table></form>");
				out.println("<body></html>");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				String sc = "<p style='color:red'>";
				sc+=" id does not exist";
				sc+="</p></body>";
				out.println(sc);
				out.println("</html>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

		
	}


	private boolean isExist(String id) {
		boolean result = false;
		ResultSet resultSet = null;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement("select id from login");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (id.equals(resultSet.getString("id"))) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
