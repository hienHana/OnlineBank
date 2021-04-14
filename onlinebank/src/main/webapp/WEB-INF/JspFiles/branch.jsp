<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Branch Form</title>
	<style type="text/css">
		.err{
			color: red;
		}
	</style>
</head>
<body>
	<form:form action="saveBranch" method="POST"
		modelAttribute="branch">
		<table >
			<tr>
				<td colspan="3" align="center">Branch Form:</td>
			</tr>
			
			<tr>
				<td>Name:</td>
				<td><form:input path="branchName" /></td>
				<td><form:errors path="branchName"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>City:</td>
				<td><form:input path="branchCity" /></td>
				<td><form:errors path="branchCity"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>State:</td>
				<td><form:input path="branchState" /></td>
				<td><form:errors path="branchState"  cssClass="error"/></td>

			</tr>
			
			<tr>
				<td>Country:</td>
				<td><form:input path="branchCountry" /></td>
				<td><form:errors path="branchCountry"  cssClass="error"/></td>
			</tr>
			
			<tr>
				<td colspan="3" align="center" ><input type="submit" value="Submit" /></td>
			</tr>
			
		</table>
	</form:form>
	<br/>
	<br/>
	<br/>
	<br/>
	<hr/>
	<br/>

	<table border="1">
		<tr >
			<th colspan="6" align="center">Branch Result:</th>
		</tr>
		<tr>
			<th>Branch ID</th>
			<th>Branch Name</th>
			<th>Branch City</th>
			<th>Branch State</th>
			<th>Branch Country</th>
			<th>Action</th>
		</tr>
	
		<c:forEach items="${branches}" var="branch">
			<tr>
				<td>${branch.branchId}</td>
				<td>${branch.branchName}</td>
				<td>${branch.branchCity}</td>
				<td>${branch.branchState}</td>
				<td>${branch.branchCountry}</td>
				<td>
					<a href="deleteBranch?id=${branch.branchId}" >delete</a>
					<a href="updateBranchForm?id=${branch.branchId}">update</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>