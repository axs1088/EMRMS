<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register Patient</title>
    <style>
        input {
            background: white;
            color: black;
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
<h3>Patient Information</h3>
<form:form method="POST" action="/emrms/addPatient" modelAttribute="patientRegistrationModel">
    <br>
    <div>
        <form:label path="lastName">Last Name: </form:label>
        <form:input path="lastName"/>
    </div>
    <br>
    <div>
        <form:label path="firstName">First Name: </form:label>
        <form:input path="firstName"/>
    </div>
    <br>
    <div>
        <form:label path="middleName">Middle Name: </form:label>
        <form:input path="middleName"/>
    </div>
    <br>
    <div>
        <form:button type="submit" value="Submit">Add Patient</form:button>
    </div>
</form:form>
</body>

</html>