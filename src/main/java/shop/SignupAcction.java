package shop;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class SignupAcction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullName = request.getParameter("fullName");
		Map<String, String[]> params = request.getParameterMap();
		String password = params.get("password")[0];
		String passwordConfirm = params.get("passwordConfirm")[0];
		if (!passwordConfirm.equals(password)) {
			response.sendRedirect("signup.jsp");

		}else {
			try {
				Class.forName("org.postgresql.Driver");
				String url = "jdbc:postgresql://localhost:5440/landing_db";
				Properties props = new Properties();
				props.setProperty("user", "postgres");
				props.setProperty("password", "1234");
				props.setProperty("ssl", "false");
				try {
					Connection conn = DriverManager.getConnection(url, props);
					Statement smt = conn.createStatement();
					smt.execute("INSERT INTO clients(name,phone,email,password) VALUES("
							+ "'"+ params.get("fullName")[0]
							+ "', '"+ params.get("phoneNumber")[0]
							+ "', '"+ params.get("emailAddress")[0]
							+ "', '" + params.get("password")[0]
							+ "')");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().append("Success! " + fullName);
		}
	}

}