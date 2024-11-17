package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;
    public RegServlet() {
        super();

    }

 	public void init(ServletConfig config) throws ServletException {
		 try {
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("first-name");
		String lname = request.getParameter("last-name");
		String gender = request.getParameter("gender");
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		PrintWriter pw = response.getWriter();

		try {
			PreparedStatement statement = connection.prepareStatement("insert into uinfo values(?,?,?,?,?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, gender);
			statement.setString(4, username);
			statement.setString(5, pwd);

			int i = statement.executeUpdate();
			pw.println("<html");
			pw.println("<body><center>");
			if(i > 0){
				pw.println("<h1>Registration Successful!</h1><br>");
				pw.println("<a href=\"login.html\">Login</a>");
				
				
			}
			else{
				pw.println("<h1>Error occurred!</h1>");
			}
			pw.println("</center></body>");
			pw.println("</html>");
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
