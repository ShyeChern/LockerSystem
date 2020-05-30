<!--
// Display all the locker history of the locker
// Author: See Di Ching
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.History"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Locker History</title>
<style>
td,th{
	border:1px solid #dddddd;
}
</style>
</head>
<body>
	<!-- Display all the history of the locker  -->
	<%
		// Get data from servlet and output as table
		@SuppressWarnings ("unchecked")
		List<History> histories =  (ArrayList<History>) request.getAttribute("histories");
		
		if(histories.size()==0){
			out.println("<h1>No History Yet</h1>");
		}
		else{
			out.println("<h1>Locker "+histories.get(0).getLocker().getNo()+" History</h1>");
		}
	
		out.println("<table>");
		out.println("<tr><th>Customer Username</th><th>Date</th><th>Time</th><th>Duration</th><th>Fee</th></tr>");
		for (History history : histories) {
			out.println("<tr><td>"+history.getCustomerUsername()+"</td><td>"+history.getDate()+"</td><td>"+history.getTime()+"</td><td>"+history.getDuration()+"</td><td>"+history.getFee()+"</td></tr>");
		}
		out.println("</table>");
	%>
	
	<a href="LockerDisplayServlet">Back to LockerDisplay</a>
</body>
</html>