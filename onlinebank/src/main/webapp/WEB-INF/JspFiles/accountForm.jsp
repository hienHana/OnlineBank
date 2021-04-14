<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Account Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
	<link rel="stylesheet" href="/css/home.css" />
	<style type="text/css" >
		.error{
			color: red;
		}
	</style>
<style type="text/css">
	.err{
		color: red;
	}
</style>
</head>
<body >
			
	<form:form action="createAccount" method="POST"
		modelAttribute="account">
		<table class="table table-dark">
			<tr>
				<td ><b>Account Form:</b></td>
			</tr>
			
			<tr>
				<td>Account Holder Name:</td>
				<td><form:input path="accountHolderName" /></td>
				<td><form:errors path="accountHolderName"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>Account Type:</td>
				<td><form:input path="accountType" /></td>
				<td><form:errors path="accountType"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>Date Opened:</td>
				<td><form:input type="date" path="accountDateOpened" /></td>
				<td><form:errors path="accountDateOpened"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>Account Current Balance:</td>
				<td><form:input path="accountCurrentBalance" /></td>
				<td><form:errors path="accountCurrentBalance"  cssClass="error"/></td>
			</tr>
			
			
			<tr>
				<td>Branch:</td>
				<td>
					<form:select path="accountBranch" required="required">
						<form:option value="None" label="Select" />
						<form:options items="${branches}"/>
					</form:select>
				</td> 				
			 	<td><form:errors path="accountBranch"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Customer ID:</td>
				<td><form:input name="customerId" path="accountCustomer.customerId" required="required"/></td>
				<td><form:errors path="accountCustomer.customerId"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>
					<a href="accountAll" class="button">Cancel</a>
					<input type="submit" value="Submit" />			
				</td>
			</tr>			
		</table>
		
		
	</form:form>
	
</body>
</html>