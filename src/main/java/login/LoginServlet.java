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
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Connection connection;
    public LoginServlet() {
    }

    public void init(ServletConfig config) throws ServletException {
		 try {
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from uinfo where username=?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			
			pw.println("<html");
			pw.println("<body><center>");
			if(rs.next()){
				pw.println("<h1>Welcome "+rs.getString("name")+"</h1>");
			}else{
				pw.println("<h1>User Not Found!</h1>");
			}
			pw.println("</center></body>");
			pw.println("</html>");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
