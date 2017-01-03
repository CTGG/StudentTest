package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

import factory.ServiceFactory;
import model.Score;
import service.ScoreManagementService;


/**
 * Servlet implementation class ShowScore
 */
@WebServlet("/ShowScore")
public class ShowScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ScoreManagementService scoreService = ServiceFactory.getScoreService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowScore() {
        super();
    }
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext Context= getServletContext();
		int guestCounter= (int) Context.getAttribute("guestCounter");
		int onlineCounter= (int) Context.getAttribute("onlineCounter");
		HttpSession session = request.getSession(false);
		if (session.getAttribute("id") == null) {
			guestCounter-=1;
			onlineCounter+=1;
			Context.setAttribute("guestCounter", guestCounter);
			Context.setAttribute("onlineCounter", onlineCounter);
		}
		
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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


	private void displayScores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean isExist = isExist(id);
		if (isExist) {
			List<Score> list = scoreService.getScoresById(id);
			boolean warning = false;
			for (Score score : list) {
				if (score.getScore()<0) {
					warning = true;
				}
			}
			request.setAttribute("scores", list);
			System.out.println("size -------- "+list.size());
			if (warning) {
				//TODO warining page
				ServletContext Context= getServletContext();			
				Context.getRequestDispatcher("/jsp/warning.jsp").forward(request, response);			
			}else {
				//TODO normal page
				ServletContext Context= getServletContext();			
				Context.getRequestDispatcher("/jsp/normal.jsp").forward(request, response);
			}

		}else {
			//error page 
			ServletContext Context= getServletContext();			
			Context.getRequestDispatcher("/user/error_no_user.html").forward(request, response);			
		}
		

		
	}


	private boolean isExist(String id) {
		boolean valid = scoreService.isExist(id);
		return valid;
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
