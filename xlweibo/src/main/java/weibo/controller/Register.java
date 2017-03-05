package weibo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.model.Account;
import weibo.model.UserService;



@SuppressWarnings("serial")
@WebServlet(
		urlPatterns={"/register.do"},
		initParams={
				@WebInitParam(name="SUCCESS_VIEW",value="success.jsp"),
				@WebInitParam(name="ERROR_VIEW",value="register.jsp")
		})
public class Register extends HttpServlet {

	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	private String USERS;
	public Register() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");
		UserService userService = (UserService)getServletContext().getAttribute("userService");
		List<String>errors = new ArrayList<String>();
		if(isInvalidEmail(email)){
			errors.add("未填写邮件或邮件格式不正确");
		}
	
		if(isInvalidPassword(password,confirmedPasswd)){
			errors.add("请确认密码符合格式并再次确认密码");
		}
	    Account account = new Account(username, password, email);
	    if (userService.isUserExisted(account)) {
	        errors.add("使用者名称为空或已存在");
	      }
		String resultPage = ERROR_VIEW;
		if(!errors.isEmpty()){
			request.setAttribute("errors", errors);
		}else{
			resultPage = SUCCESS_VIEW;
			userService.add(account);
		}
		request.getRequestDispatcher(resultPage).forward(request, response);
	}
    private boolean isInvalidEmail(String email) {
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                        + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }

	private boolean isInvalidPassword(String password,String confirmedPasswd){
		return password == null ||
				password.length()<6 ||
				password.length()>16 ||
				!password.equals(confirmedPasswd);
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		USERS = getServletContext().getInitParameter("USERS");
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
		
	}

}
