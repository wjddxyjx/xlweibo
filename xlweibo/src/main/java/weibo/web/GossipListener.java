package weibo.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import weibo.model.AccountDAOjdbcImpl;
import weibo.model.BlahDAOjdbcImpl;
import weibo.model.UserService;


@WebListener
public class GossipListener implements ServletContextListener {

	 
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
   
	}
 
	public void contextInitialized(ServletContextEvent sce) {
		Context initContext;
	    try
	    {
	      initContext = new InitialContext();
	      Context envContext = (Context)
	        initContext.lookup("java:/comp/env");
	      DataSource dataSource = (DataSource)envContext.lookup("jdbc/demo");
	      ServletContext context = sce.getServletContext();
	      context.setAttribute("userService", 
	        new UserService(new AccountDAOjdbcImpl(dataSource), new BlahDAOjdbcImpl(dataSource)));
	    } catch (NamingException ex) {
	      throw new RuntimeException(ex);
	    }
	}

}
