<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>
<%

try {
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
	PreparedStatement pst=con.prepareStatement
			("select name from faculty where email=?");
	String password=request.getParameter("pass");
	String email=request.getParameter("mail");
	pst.setString(1,email);
	ResultSet rs=pst.executeQuery();
	rs.next();
	String name=rs.getString(1);
	
	out.print("Welcome "+name+"!!!"); 
	
}catch(Exception e)
{
     out.print(e.toString());
}

%>
</th></tr>
<tr><td>
<a href="Newstudent.html">Add a new Student</a> </td></tr>
<tr><td>                              
<a href="Updations.html">Student details updation</a></td></tr>
<tr><td>    
<a href="Deletions.html">deleting a student</a></td></tr>
<tr><td>
<a href="AttendanceUpdation.html">Updating attendance</a></td></tr>
<tr><td>
<a href="Reports.html">Student Reports</a></td></tr>

</table>
</body>
</html>