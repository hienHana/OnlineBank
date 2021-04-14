<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/home.css" />
	<style type="text/css" >
		.error{
			color: red;
		}
	</style>
</head>
<body>
<!-- long customerId; String firstName; String lastName; Address adrress; String gender; String dob; String email;String phone;
	
 String SSN; @OneToMany @JoinColumn(name="accountId") private List<Account> accounts = new ArrayList<>();//for login @OneToOne private User user; -->
 
 	<form:form action="createCustomer" method="post" modelAttribute="customer">
 		<table class="table table-dark">
 			<tr><th colspan="3" >Customer Details Form</th></tr>
 			<tr>
 				<td>First Name:</td>
 				<td><form:input type="text" path="firstName"/></td>
 				<td><form:errors path="firstName" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>Last Name:</td>
 				<td><form:input type="text" path="lastName"/></td>
 				<td><form:errors path="lastName" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>Address:</td>				
				<td ><form:input type="text" placeholder="AddressLine1" path="adrress.addressLine1"/></td >
				<td ><form:input type="text" placeholder="AddressLine2" path="adrress.addressLine2"/></td >
				<td ><form:input type="text" placeholder="City" path="adrress.city"/></td >
				<td ><form:input type="text" placeholder="State" path="adrress.state"/></td >
				<td ><form:input type="text" placeholder="Zipcode" path="adrress.zipcode"/></td > 				
 				<td><form:errors path="adrress" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
				<td>Gender:</td>
				<td>
					<form:radiobutton name="gender" path="gender" value="male" label="Male" /> 
					<form:radiobutton name="gender" path="gender" value="female" label="Female" /></td>
				<td><form:errors path="gender" cssClass="error" /></td>
			</tr>
 			
 			<tr>
 				<td>DOB:</td>
 				<td><form:input type="date" path="dob"/></td>
 				<td><form:errors path="dob" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>Email:</td>
 				<td><form:input type="text" path="email"/></td>
 				<td><form:errors path="email" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>Phone:</td>
 				<td><form:input type="text" path="phone"/></td>
 				<td><form:errors path="phone" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>SSN:</td>
 				<td><form:input type="text" path="ssn"/></td>
 				<td><form:errors path="ssn" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td>UserId:</td>
 				<td><form:input name= "userId" required="required" path="user"/></td>
 				<td><form:errors path="user" cssClass="error"/></td>
 			</tr>
 			
 			<tr>
 				<td > 
 					<a href="accountAll" class="button">Cancel</a>
					<input type="submit" value="Next" />
				</td>
 			</tr>
 			
 		</table>
 	</form:form>
</body>
</html>