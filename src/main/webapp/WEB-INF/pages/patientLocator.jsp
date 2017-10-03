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
        table{
            color:black;
        }
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
<form:form id="patientLocatorForm" method="post" action="patientLocatorProcess" modelAttribute="census">
    <h3> Patient Information</h3>
    <div>
        <form:label path="lastName">Last Name: </form:label>
        <form:input path="lastName"/>
        <form:label path="firstName">First Name: </form:label>
        <form:input path="firstName"/>
    </div>
    <div>
        <form:label path="gender">Gender: </form:label>
        <form:select path="gender">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
        </form:select>
    </div>

    <h3> Address</h3>

    <br>
    <div>
        <form:button id="findPatient" name="findPatient" >Find Patient</form:button>
    </div>
</form:form>

</br></br>
<div id="header" align="center" class="header">
    <h3><span>Patient Locator Search Results</span></h3>
</div>
<table border="1" align="center">
    <tr>
        <th>Name</th>
        <th>DOB</th>
        <th>Gender</th>
        <th>Location</th>
        <th>MRN</th>
        <th>Encounter</th>
    </tr>
    <c:forEach items="${hPatientList}" var="patient" varStatus="status">
        <tr>
            <td width="130">${patient.lastName}, ${patient.firstName}</td>
            <td width="150">${patient.birthdate}</td>
            <td width="100">
                <c:choose>
                    <c:when test="${patient.gender == '1'}">
                        Male
                    </c:when>
                    <c:otherwise>
                        Female
                    </c:otherwise>
                </c:choose>
            </td>
            <td width="100"></td>
            <td width="100">${patient.MPINo}</td>
            <td width="100">
                <c:choose>
                    <c:when test="${patient.encStatus == '1'}">
                        Active
                    </c:when>
                    <c:otherwise>
                        Closed
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>