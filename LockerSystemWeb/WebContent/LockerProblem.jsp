<!--
// Display and allow user to choose and update the locker condition
// Author: See Di Ching
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Status"%>
<%@ page import="model.Locker"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Locker Problem</title>
</head>
<body>
	<!-- Allow user to update locker condition with given selection -->
	<%
		// Get data from servlet
		Locker locker=(Locker)request.getAttribute("locker");
	%>
	<h3>What is the problem on locker <% out.print(locker.getNo()); %> ?</h3>
	
	<form method="post" action="LockerUpdateProblemServlet">
		<input type="hidden" name="lockerId" value="<%= locker.getLockerId() %>">
		<select name="problem">
			
			<%
				// get all the problem and display as option
				@SuppressWarnings ("unchecked")
				List<Status> problems =  (ArrayList<Status>) request.getAttribute("problems");
				for (Status problem : problems) {
					out.println("<option value='"+problem.getStatusId()+"'>"+problem.getDescription()+"</option>");
				}
			%>
	
		</select>
		
		<input type="submit" value="Submit">
	</form>
	
	<br><br>
	<a href="LockerDisplayServlet">Back to Display</a>
	

</body>
</html>