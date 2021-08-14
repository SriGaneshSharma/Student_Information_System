package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServelt
 */
public class StudentServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServelt() {
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
				("insert into student values(?,?,?,?,?,?)");		
			int id=Integer.parseInt(request.getParameter("sid"));
			String name=request.getParameter("sname");
			String gen=request.getParameter("gen");
			String email=request.getParameter("semail");
			String dob=request.getParameter("sdate");
			int phn=Integer.parseInt(request.getParameter("sphn"));
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, gen);
			ps.setString(4, email);
			LocalDate localDate=LocalDate.parse(  dob.replaceAll("-", "/") , DateTimeFormatter.ofPattern( "dd/MM/uuuu" ) );
			Date sqlDate=Date.valueOf(localDate);
			ps.setDate(5, sqlDate);
			ps.setInt(6, phn);
			int pst=ps.executeUpdate();
			
			if(pst==1)
			{
				RequestDispatcher view = request.getRequestDispatcher("resultJSP.jsp");
				request.setAttribute("name", name);
				request.setAttribute("id", id);
				request.setAttribute("result", "Your successfully inserted a record into database");
				view.forward(request, response);
			}
			
			
		}catch(Exception e)
		{
			RequestDispatcher view = request.getRequestDispatcher("resultJSP.jsp");
			request.setAttribute("result", "A error during insertion of record");
			request.setAttribute("error",e);
			view.forward(request, response);
		}
		
		
		
	}

}
