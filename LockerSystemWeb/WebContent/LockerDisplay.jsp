<!--
// Display Locker Information in the page
// Author: Mujeeb Ali Najm Al-Qarah 
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Locker"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Locker</title>
<style>
td,th{
	border:1px solid #dddddd;
}
</style>
</head>
<body>
	<!-- Display all the locker, locker condition  -->
	<!-- Allow user to update locker condition  -->
	<!-- Allow user to add new locker  -->
	<h2>All Locker</h2>
	<%
		// Get data from servlet and display as table
		@SuppressWarnings ("unchecked")
		List<Locker> lockers =  (ArrayList<Locker>) request.getAttribute("lockers");
		String result =  (String) request.getAttribute("result");
		
		out.println("<table>");
		out.println("<tr><th>Locker No</th><th>Condition</th></tr>");
		for (Locker locker : lockers) {
			out.println("<tr><td>"+locker.getNo()+"</td><td>"+locker.getStatus().getDescription()+"</td><td><a href='ViewLockerHistoryServlet?lockerId="+locker.getLockerId()+"'>View Locker History</a></td><td><a href='LockerSelectProblemServlet?lockerId="+locker.getLockerId()+"'>Update Problem</a></td></tr>");
		}
		out.println("</table>");
	%>
	<p><% out.print(result); %></p>
	
	<br>
	
	<h3>Add New Locker</h3>
	<form method="post" action='AddLockerServlet'>
	
		Locker No
		<input type="number" name="lockerNo" required><br>
		
		<input type="submit" value="Add">
	
	</form>
	
	<br>
	<br>
	
	<a href="index.jsp">Back to Main Menu</a>

</body>
</html>