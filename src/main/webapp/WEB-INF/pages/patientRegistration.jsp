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
<form:form method="POST" action="/emrms/addPatient" modelAttribute="patientRegistrationModel">
    <h3> Patient Information</h3>
    <div>
        <form:label path="lastName">Last Name: </form:label>
        <form:input path="lastName"/>
        <form:label path="firstName">First Name: </form:label>
        <form:input path="firstName"/>
    </div>
    <div>
        <form:label path="middleName">Middle Name: </form:label>
        <form:input path="middleName"/>
        <form:label path="gender">Gender: </form:label>
        <form:select path="gender">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
        </form:select>
    </div>
    <div>
        <form:label path="birthDate">Birth Date: </form:label>
        <form:input type="date" path="birthDate"/>
    </div>
    <h3> Address</h3>
    <div>
        <form:label path="streetAddress">Street Address: </form:label>
        <form:input path="streetAddress"/>
        <form:checkbox path="mailingMatchesStreet" label="Mailing?                             "/>
    </div>
    <div>
        <form:label path="city">City/State/Zip: </form:label>
        <form:input path="city"/>
        <form:input path="state"/>
        <form:input path="zip"/>
    </div>
    <div>
        <form:label path="cellPhone">Cell Phone: </form:label>
        <form:input path="cellPhone"/>
        <form:label path="homePhone">Home Phone: </form:label>
        <form:input path="homePhone"/>
    </div>
    <div>
        <form:label path="email">Email: </form:label>
        <form:input path="email"/>
    </div>
    </div>
    <h3> Miscellaneous</h3>
    <div>
        <form:label path="organDonor">Organ Donor: </form:label>
        <form:select path="organDonor">
            <form:option value="true" label="Yes"/>
            <form:option value="false" label="No"/>
        </form:select>
    </div>
    <br>
    <div>
        <form:button type="submit" value="Submit">Add Patient</form:button>
    </div>
</form:form>
</body>

</html>