

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class DbContextListener
 *
 */
public class DbContextListener implements ServletContextListener {
	
	private static Connection conn;
	
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    	ServletContext context = sce.getServletContext();
	
		String url = context.getInitParameter("url");
		Properties props = new Properties();
		props.setProperty("user", context.getInitParameter("user"));
		props.setProperty("password", context.getInitParameter("password"));
		props.setProperty("ssl", context.getInitParameter("ssl"));
		
		if(conn == null) {
			try {
				Class.forName(context.getInitParameter("driverName"));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.setAttribute("conn", conn);
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         Connection connection = (Connection)sce.getServletContext().getAttribute("conn");
         try {
			if(!connection.isClosed()) {
				 connection.close();
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
