<!--
// Display and allow user to update the locker condition
// Author: Lim Shye Chern
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Status"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fix Locker</title>
</head>
<body>
	<!-- Allow user to choose to update and fix the locker according to the condition  -->
	<%
		// Get data from parameter
		int lockerId =  Integer.parseInt(request.getParameter("lockerId"));
	%>
	<form method="post" action="UpdateProblemLockerServlet">
		<input type="hidden" name="lockerId" value="<%= lockerId %>">
		Update the locker status to 
		<select name="status">
			<%
				// Get data from servlet
				@SuppressWarnings ("unchecked")
				List<Status> works =  (ArrayList<Status>) request.getAttribute("works");
			
				for(Status status:works){
					out.println("<option value='"+status.getStatusId()+"'>"+status.getDescription()+"</option>");
				}
			%>
		</select>
		?
		
		<input type="submit" value="Yes">
	</form>
		
	
	<a href="ViewReportLockerServlet">No</a>

</body>
</html>