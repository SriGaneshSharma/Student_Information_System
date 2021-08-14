package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServelt
 */
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out=response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			PreparedStatement ps=con.prepareStatement
				("select password,admistrator from faculty where email=?");		
			String password=request.getParameter("pass");
			String email=request.getParameter("mail");
			ps.setString(1,email);
			String pass,ads;
			ResultSet rs=ps.executeQuery();
			rs.next();
			pass=rs.getString(1);
			 ads=rs.getString(2);
			if(pass.equals(password))
			{
				if(ads.equalsIgnoreCase("yes"))
				{
					RequestDispatcher view = request.getRequestDispatcher("AdmJSP.jsp");
			          view.forward(request, response);
				}
				else{
					RequestDispatcher view = request.getRequestDispatcher("LoginJSP.jsp");
			          view.forward(request, response);
				}
			}
			else
			{
				RequestDispatcher view = request.getRequestDispatcher("InvalidUserJSP.jsp");
		          view.forward(request, response);
			}
		
			
		}catch(Exception e)
		{
			RequestDispatcher view = request.getRequestDispatcher("InvalidUserJSP.jsp");
	          view.forward(request, response);
		}

		
	}

}
