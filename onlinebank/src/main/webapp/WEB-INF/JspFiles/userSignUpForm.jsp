<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
	<link rel="stylesheet" href="/css/home.css" />
	<style type="text/css" >
		.error{
			color: red;
		}
	</style>
</head>

<body>
	<form:form action="createUserSignUp" method="POST" modelAttribute="user">
		<table class="table table-dark">
			<tr><th colspan="3">User Login Credential</th></tr>
			<tr>
				<td>User Name: </td>
				<td><form:input type="text" path="username"/></td>
				<td><form:errors path="username"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Password:</td>
				<td><form:input type="text" path="password"/></td>
				<td><form:errors path="password"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Email:</td>
				<td><form:input type="text" path="email"/></td>
				<td><form:errors path="email"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Phone:</td>
				<td><form:input type="text" path="phone"/></td>
				<td><form:errors path="phone"  cssClass="error"/></td>
			</tr>
			
			<%-- <tr>
				<td>Customer Id:</td>
				<td><form:input path="customer.customerId"/></td>
				<td><form:errors path="customer.customerId"  cssClass="error"/></td>
			</tr> --%>
			
			<tr>
				<td>
					<a href="/bank" class="button">Cancel</a>
					<input type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>