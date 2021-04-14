<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Withdraw Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
	<link rel="stylesheet" href="/css/home.css" />
	<style type="text/css">
		.error{
			color: red;
		}
	</style>
</head>
<body>
	<form:form action="withdraw" method="POST" modelAttribute="account">
		<table class="table table-dark">
			<tr>
				<td ><strong>Withdraw Form: </strong></td>
			</tr>
		
			<tr>
				<td>Account Id: </td>
				<td><form:input type="number" path="accountId" required="required"/></td>
				<td><form:errors path="accountId"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Withdraw Amount: </td>
				<td><form:input type="number" path="accountCurrentBalance" required="required"/></td>
				<td><form:errors path="accountCurrentBalance"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td></td>
				<td> 
					<a href="/bank" class="button">Cancel</a>
					<input type="submit" value="Submit" />
				</td>
			</tr>
			
		</table>
	</form:form>
	
</body>
</html>