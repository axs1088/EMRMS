<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Locate Patient</title>
    <script type="text/javascript">
        function validateInput() {
            var lastName = document.getElementById("lastNameTxt").value;
            var firstName = document.getElementById("firstNameTxt").value;
            if (lastName == "" || firstName == "") {
                alert("Please fill in mandatory fields marked by *");
                return false;
            } else {
                return true;
            }

        }
    </script>
    <style>
        <%@include file="/css/formCommon.css"%>

        .linkColor {
            color: white;
        }

    </style>
</head>

<%@include file="siteHeader.jsp" %>

<body>
<div class="content">
    <form:form id="patientLocatorForm" method="post" action="patientLocatorProcess" modelAttribute="census"
               onsubmit="return validateInput()">
    <table border="1" cellpadding="5" align="center">
        <th colspan="2"><h3> Patient Information</h3></th>
        <tr>
            <div>
                <td><form:label path="lastName">Last Name: </form:label>
                    <mandatory> *</mandatory>
                </td>
                <td><form:input id="lastNameTxt" path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name: </form:label>
                <mandatory> *</mandatory>
            </td>
            <td><form:input id="firstNameTxt" path="firstName"/></td>
        </tr>
</div>
<tr>
    <div>
        <td><form:label path="gender">Gender: </form:label></td>
        <td><form:select path="gender">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
        </form:select></td>
    </div>
</tr>
<br>
<tr>
    <td colspan="2" align="center">
        <div>
            <form:button id="findPatient" name="findPatient">Find Patient</form:button>
        </div>
    </td>
</tr>
</table>
</form:form>
<div id="header" align="center" class="header">
    <h3><span>Patient Locator Search Results</span></h3>
</div>
<table border="1" align="center" cellpadding="5">
    <tr>
        <th>
            <text>Name</text>
        </th>
        <th>
            <text>DOB</text>
        </th>
        <th>
            <text>Gender</text>
        </th>
        <th>
            <text>Location</text>
        </th>
        <th>
            <text>MRN</text>
        </th>
        <th>
            <text>Encounter</text>
        </th>
    </tr>
    <c:forEach items="${hPatientList}" var="patient" varStatus="status">
        <tr>
            <td width="130">
                <text><a
                        href="/emrms/patientDetails?hPatientID=${patient.hPatientID}">${patient.lastName}, ${patient.firstName}</a>
                </text>
            </td>
            <td width="150">
                <text>${patient.birthdate}</text>
            </td>
            <td width="100">
                <text>
                    <c:choose>
                        <c:when test="${patient.gender == '1'}">
                            Male
                        </c:when>
                        <c:otherwise>
                            Female
                        </c:otherwise>
                    </c:choose>
                </text>
            </td>
            <td width="100"></td>
            <td width="100">
                <text>${patient.MPINo}</text>
            </td>
            <td width="100">
                <text>
                    <c:choose>
                        <c:when test="${patient.encStatus == '1'}">
                            Active
                        </c:when>
                        <c:when test="${patient.encStatus == '2'}">
                            Closed
                        </c:when>
                        <c:when test="${patient.encStatus == '2'}">
                            Scheduled
                        </c:when>
                    </c:choose>
                </text>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

<%@include file="siteFooter.jsp" %>

</body>
</html>