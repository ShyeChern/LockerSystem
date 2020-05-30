<!--
// Display all the locker with some problem in report
// Author: Lim Shye Chern
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Locker"%>
<%@ page import="model.Status"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Locker Report</title>
<style>
td,th{
	border:1px solid #dddddd;
}
</style>
</head>
<body>
	<!-- Display all the with problem  -->
	
	<h1>Locker Report</h1>
	<%
		// Get data from servlet
		@SuppressWarnings ("unchecked")
		List<Locker> maintenances =  (ArrayList<Locker>) request.getAttribute("maintenances");
		@SuppressWarnings ("unchecked")
		List<Locker> repairs =  (ArrayList<Locker>) request.getAttribute("repairs");
		@SuppressWarnings ("unchecked")
		List<Locker> defects =  (ArrayList<Locker>) request.getAttribute("defects");
		
		String result =  (String) request.getAttribute("result");
		out.print("<p>"+result+"</p>");
	%>
	<h3>Locker that are in maintenance</h3>
	<% 
		for (Locker maintenance : maintenances) {
			out.println(maintenance.getNo()+"\t<a href='LockerUpdateServlet?lockerId="+maintenance.getLockerId()+"'>Update Locker Condition</a><br>");
		}
	%>
	<br><br>
	<h3>Locker that need to repair</h3>
	<% 
		for (Locker repair : repairs) {
			out.println(repair.getNo()+"\t<a href='LockerUpdateServlet?lockerId="+repair.getLockerId()+"'>Update Locker Condition</a><br>");
		}
	%>
	<br><br>
	<h3>Locker that already defect (Cannot be used anymore)</h3>
	<% 
		for (Locker defect : defects) {
			out.println(defect.getNo()+"\t<a href='DeleteLockerServlet?lockerId="+defect.getLockerId()+"'>Delete Locker</a><br>");
		}
	%>
	
	<br><br><br><br>
	<a href="index.jsp">Back to Menu</a>


</body>
</html>