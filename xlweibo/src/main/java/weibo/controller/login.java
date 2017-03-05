package weibo.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.model.Account;
import weibo.model.UserService;




@SuppressWarnings("serial")
@WebServlet(
    urlPatterns={"/login.do"},
    initParams={
        @WebInitParam(name = "SUCCESS_VIEW", value = "message.do"),
        @WebInitParam(name = "ERROR_VIEW", value = "index.jsp")
    }
)
public class login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;
    
    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
    
	protected void doPost(HttpServletRequest request, 
	                      HttpServletResponse response) 
	                        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String page = ERROR_VIEW;
	    Account account = new Account();
	    account.setName(username);
	    account.setPassword(password);
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    if(userService.checkLogin(account)) {
	        request.getSession().setAttribute("login", username);
	        page = SUCCESS_VIEW;
	    }
	    else{
	    	request.setAttribute("error","√˚≥∆ªÚ√‹¬Î¥ÌŒÛ");
	    }
	    response.sendRedirect(page);
	}
}
