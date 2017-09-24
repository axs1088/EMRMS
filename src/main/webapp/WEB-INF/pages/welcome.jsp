<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
		 
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
			<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
            <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
        <style>
			
			table, th, td {
				background:black;
				color:white;
				font-family: 'latoregular';
				font-size: 14px;
    			border-collapse: collapse;
    			padding: 15px;
    			text-align: left;
				border-bottom:2pt solid white;border-top:2pt solid white;
			}
        </style>
    </head>
    <body class="body" align="center">
	<div id="header">
		<h1><a href="/emrms/home">EMRMS<span>Electronic Medical Record Management System</span></a></h1>
		<ul id="navigation">
			<li class="current">
				<a href="/emrms/home">Home</a>
				<a href="/emrms/PatientLocator">Patient Locator</a>
				<h3><a href="#">${census}</a></h3>
			</li>
		</ul>
		
		</div>
        
        <table cellpadding="10" border="1" align="center">
		    <tr style=>
		        <th>Name</th>
		        <th>DOB</th>
		        <th>Gender</th>
		        <th>Location</th>
		        <th>MRN</th>
		        <th>Encounter</th>
		    </tr>
		    <c:forEach items="${hCensusList}" var="physicianCensus" varStatus="status">
		        <tr>
		            <td width="130">${physicianCensus.lastName}, ${physicianCensus.firstName}</td>
		            <td width="100">${physicianCensus.birthdate}</td>
		            <td width="100">
			            <c:choose>
				            <c:when test="${physicianCensus.gender == '1'}">
							  Male
							</c:when>
							<c:otherwise>
								Female
							</c:otherwise>
						</c:choose>
					</td>
		            <td width="100"></td>
		            <td width="100">${physicianCensus.mPINo}</td>
		            <td width="100">
			            <c:choose>
				            <c:when test="${physicianCensus.encStatus == '1'}">
							  	Active
							</c:when>
							<c:otherwise>
								Not Active
							</c:otherwise>
						</c:choose>
					</td>
		        </tr>
		    </c:forEach>
		</table>
		<div id="footer">
				<div>
					<span>Malvern Clinic, PA - 19355 | 222-222-2222</span>
					<p>
						&copy; 2017 by EMRMS TEAM &amp; SWENG-500 - Penn State University. All rights reserved.
					</p>
				</div>
			<div id="connect">
			<a href="https://freewebsitetemplates.com/go/facebook/" id="facebook" target="_blank">Facebook</a>
			<a href="https://freewebsitetemplates.com/go/twitter/" id="twitter" target="_blank">Twitter</a>
			<a href="https://freewebsitetemplates.com/go/googleplus/" id="googleplus" target="_blank">Google&#43;</a>
			<a href="https://freewebsitetemplates.com/go/pinterest/" id="pinterest" target="_blank">Pinterest</a>
			</div>
		</div>
    </body>
    </html>