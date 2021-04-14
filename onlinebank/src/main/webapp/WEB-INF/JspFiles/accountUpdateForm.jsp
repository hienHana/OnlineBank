<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Account Form</title>
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
<body>
			
	<form:form action="updateAccount" method="POST"
		modelAttribute="account">
		<table border="1">
			<tr>
				<td colspan="3" align="center"><b>Account Form:</b></td>
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
				<td><form:input path="accountBranch" /></td>
				<td><form:errors path="accountBranch"  cssClass="error"/></td>
			</tr>
			
			<%-- <tr>
				<td>Branch:</td>
				<td>
					<form:select path="accountBranch">
						<form:option value="None" label="Select" />
						<form:options items="${brachList}"/>
					</form:select>
				</td> 				
			 	<td><form:errors path="accountBranch"  cssClass="error"/></td>
			</tr> --%>
			
			<tr>
				<td>Customer:</td>
				<td><form:input path="accountCustomer" /></td>
				<td><form:errors path="accountCustomer"  cssClass="error"/></td>
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
			<th>Account ID</th>
			<th>Account Holder Name</th>
			<th>Account Type</th>
			<th>Account Date Open</th>
			<th>Account Current Balance</th>
			<th>BranchId</th>
			<th>CustomerId</th>
			<th>Action</th>
		</tr>
	
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td>${account.accountId}</td>
				<td>${account.accountHolderName}</td>
				<td>${account.accountType}</td>
				<td>${account.accountDateOpened}</td>
				<td>${account.accountCurrentBalance}</td>
				<td>${account.accountBranch}</td>
				<td>${account.accountCustomer}</td>
				<td>
					
					<a href="depositForm?id=${account.accountId}">deposit</a>
					<a href="withdrawForm?id=${account.accountId}">withdraw</a>

				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>