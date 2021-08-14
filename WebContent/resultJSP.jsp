<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result Page</title>
</head>
<body>
<%
String result=String.valueOf(request.getAttribute("result"));
out.print(result);
%>
<br>
<%
if(result.equals("Your successfully inserted a record into database"))
{
String name=String.valueOf(request.getAttribute("name"));
out.println("Student name: "+name);

String id=String.valueOf(request.getAttribute("id"));
out.println("Student id is: "+id);
}
else
{
	String error=String.valueOf(request.getAttribute("error"));
	out.print("Error is: "+error);
}
%>

</body>
</html>