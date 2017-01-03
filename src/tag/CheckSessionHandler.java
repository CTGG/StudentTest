package tag;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyTagSupport;
public class CheckSessionHandler extends BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO
	public int doEndTag() {
		
		
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession(true);
		HttpServletResponse response =  (HttpServletResponse) pageContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (session.getAttribute("id") == null) {
			ServletContext Context = pageContext.getServletContext();
			int guestCounter= (int) Context.getAttribute("guestCounter");
			int totalCounter = (int) Context.getAttribute("totalCounter");
			int onlineCounter = (int) Context.getAttribute("onlineCounter");
			totalCounter+=1;
			guestCounter+=1;
			Context.setAttribute("totalCounter", totalCounter);
			Context.setAttribute("guestCounter", guestCounter);
			System.out.println("skip page-------------");
			
			out.println("<p>总人数:	 " + totalCounter + "</p>");
			out.println("<p>已登录人数 :	" + onlineCounter + "</p>");
			out.println("</p>游客人数 :		" + guestCounter + "</p>");
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
		
	}

}
