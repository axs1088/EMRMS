<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Patient Details</title>
	
    <style>
        input {
            background: #fffdd7;
            color: black;
        }

        select {
            background: #fffdd7;
            color: black;
        }

        h3 {
            color: white;
        }

        label {
            display: inline-block;
            width: 150px;
            text-align: right;
            color: white;
        }

        text {
            color: white;
            font: Arial;
        }

        body {
            margin-left: 50px;
        }
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
		mandatory{
			color:red;
		}
    </style>
</head>

<div id="header">
    <h1><a href="/emrms/home">EMRMS<span>Electronic Medical Record Management System</span></a></h1>
    <ul id="navigation">
        <li class="current">
            <a href="/emrms/home">Home</a>
        </li>
    </ul>
</div>

<body>
	<table border="1" align="left" cellpadding="2" width="60%">
    <tr>
     	<td><text>First Name :</text></td>
		<td>${firstName}</td>
    </tr>
	<tr>
     	<td><text>Last Name :</text></td>
		<td>${lastName}</td>
    </tr>
	<tr>
     	<td><text>Middle Name :</text></td>
		<td>${middleName}</td>
    </tr>
	<tr>
     	<td><text>Gender :</text></td>
		<td>${gender}</td>
    </tr>
	<tr>
     	<td><text>Date of Birth :</text></td>
		<td>${dateOfBirth}</td>
    </tr>
	<tr>
     	<td><text>Street Address :</text></td>
		<td>${streetAddressLine1} </br>
			${streetAddressLine2} </br>
			${city} </br>
			${state}</br>
			${zip}</br>
			
		</td>
    </tr>
	<tr>
     	<td><text>Cellphone :</text></td>
		<td>${cellphone}</td>
    </tr>
	<tr>
     	<td><text>Email :</text></td>
		<td>${email}</td>
    </tr>
    </table>
	</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<a href="/emrms/patientLocator" align="centre"><text>Go Back<text></a>
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


</br></br>
</body>

</html>