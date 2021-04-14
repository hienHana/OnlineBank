<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="/css/account.css" />
</head>
	
<body class="background">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	   
	   <div class="navbar-header">
			<img src="/images/silvercoinbank.png"
				class="img-responsive logo" 
				alt="SilverCoinBank"  
				height="100" 
				width="120"/>
	   </div>
	   
	    <ul class="nav navbar-nav navbar-right">
	      <li class="nav-item">
	        <a class="nav-link"><strong class="welcome">${pageContext.request.userPrincipal.name}, welcome! </strong></a>
	      </li>      
	      <li class="nav-item active">
	        <a class="nav-link" href="/bank">Home <span class="sr-only">(current)</span></a>
	      </li>
		  <li class="nav-item">
	        <a class="nav-link" href="admin">Admin</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="userSignUpForm">Sign Up</a>
	      </li>
	      <c:if test="${empty pageContext.request.userPrincipal.name}">
		      <li class="nav-item">
		        <a class="nav-link" href="login">Login</a>
		      </li>
	      </c:if>
	      <c:if test="${not empty pageContext.request.userPrincipal.name}">
		      <li class="nav-item">
		        <a class="nav-link" href="login?logout">Logout</a>
		      </li>
	      </c:if>
	    </ul>
	    
	  </div>
	</nav>
	<div class="container" align="center">
		<c:if test="${not empty pageContext.request.userPrincipal.name}">
				<%-- <h5 class="welcome">
					${pageContext.request.userPrincipal.name}, welcome! 
				</h5>	 --%>
	
		<table>
			<tr>
				<sec:authorize access="hasAuthority('user')">				
					<td><a href="/getAccountDetailByCustomerId">View Account Detail</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="/depositForm">Deposit</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="/withdrawForm">Withdraw</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="/transferForm">Transfer</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>				
				</sec:authorize>
		
				<sec:authorize access="hasAuthority('admin')">			
					<td><a href="/userForm">Create an Account</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="/accountAll">View All Accounts</a></td>			
				</sec:authorize>
			</tr>
		</table>
		
		</c:if>
		
		</br>
		</br>
		<p><strong>All Account Summary</strong></p>
		<table border="1">
			<tr>
				<th>Account ID</th>
				<th>Account Holder Name</th>
				<th>Account Type</th>
				<th>Account Date Open</th>
				<th>Account Current Balance</th>
				<th>Branch Name</th>
				<th>Customer Full Name</th>
			</tr>
		
			<c:forEach items="${accounts}" var="account">
				<tr>
					<td>${account.accountId}</td>
					<td>${account.accountHolderName}</td>
					<td>${account.accountType}</td>
					<td>${account.accountDateOpened}</td>
					<td>${account.accountCurrentBalance}</td>
					<td>${account.accountBranch.branchName} - ID${account.accountBranch.branchId}</td>
					<td>${account.accountCustomer.firstName} ${account.accountCustomer.lastName} - ID${account.accountCustomer.customerId}</td>
				</tr>
			</c:forEach>
		</table>
		
		
	</div>

</body>
</html>