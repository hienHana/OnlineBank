<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="/css/home.css" />
</head>

<body >

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	   
	   <div class="navbar-header">
			<img src="/images/silvercoinbank.png"
				class="img-responsive logo" 
				alt="SilverCoinBank"  
				height="150" 
				width="170"/>
	   </div>
	   
	    <ul class="nav navbar-nav navbar-right">	      
	      <li class="nav-item active">
	        <a class="nav-link" href="/bank">Home <span class="sr-only">(current)</span></a>
	      </li>
		  <li class="nav-item">
	        <a class="nav-link" href="login">User</a>
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
	<div class="loadimage">
		<div class="content">	
		
			<!-- if not login -->
			<c:if test="${empty pageContext.request.userPrincipal.name}">
				<h2 class="font-weight-bold">Admin Home Page</h2>
			
				<br/>
				<br/>
				<p class="existing" > Admin? Login</p>
				
				
				<!-- <div class=".container" >
					<a href="login">Login</a>
				</div> -->
				<br>From Request Object: <%=request.getUserPrincipal().getName() %>
				
				<form name="adminLogin" action="adminLogin" method="POST">
					<div class=".container-fluid">
						<table>
							<tr>
								<td>User Name:</td>
								<td><input type="text" name="username"/></td>
							</tr>
							
							<tr>
								<td>Password:</td>
								<td><input type="password" name="password"/></td>
							</tr>
							
							<tr>
								<td colspan="2" align="center">
								<input type="submit" value="login"/>
								</td>
							</tr>
						</table>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<!-- 
					csrf = cross site resource forgery 
					When you use  spring form, there is no need to use csrf parameters because spring generates them for you.
					-->
					</div>
				</form>		
			</c:if>				
			
			<!-- if login -->			
			<c:if test="${not empty pageContext.request.userPrincipal.name}">
				<h2 class="font-weight-bold">
					${pageContext.request.userPrincipal.name}, welcome! 
				</h2>
				<a href="login?logout">logout</a>	
				
				
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
		
		</div>
	</div>
</body>
</html>