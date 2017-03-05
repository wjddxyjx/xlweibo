package weibo.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.model.Blah;
import weibo.model.UserService;



@SuppressWarnings("serial")
@WebServlet(
		urlPatterns={"/message.do"},
		initParams={
				@WebInitParam(name="MEMBER_VIEW",value="member.jsp"),
				
		})
public class message extends HttpServlet {
	private String MEMBER_VIEW;
	/**
	 * Constructor of the object.
	 */
	protected void processRequest(HttpServletRequest request,
								HttpServletResponse response)
							throws ServletException,IOException{
		String username = (String)request.getSession().getAttribute("login");
		UserService userService = (UserService)getServletContext().getAttribute("userService");
		Blah blah = new Blah();
		blah.setUsername(username);
		String blabla = request.getParameter("blabla");
	    if(blabla != null && blabla.length() != 0) {
	        if(blabla.length() < 140) {
	            blah.setDate(new Date());
	            blah.setTxt(blabla);
	            userService.addBlah(blah);
	        }
	        else {
	        	request.setAttribute("blabla", blabla);
	        }
	    }
	    List<Blah>blahs = userService.getBlahs(blah);
	    request.setAttribute("blahs", blahs);
	    request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
	}
	public message() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request,response);
		
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	       MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
	        
	}

}
