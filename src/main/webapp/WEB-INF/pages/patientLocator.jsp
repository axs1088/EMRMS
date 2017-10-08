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
		function validateInput(){
			var lastName = document.getElementById("lastNameTxt").value;
			var firstName = document.getElementById("firstNameTxt").value;
			if(lastName == "" || firstName ==""){
				alert("Please fill in mandatory fields marked by *");
				return false;
			}else{
				return true;	
			}
			
		}
	</script>
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
    			text-align: center;
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
<form:form id="patientLocatorForm" method="post" action="patientLocatorProcess" modelAttribute="census" onsubmit="return validateInput()">
	<table border="1" cellpadding="5" align="center">
    <th colspan="2"><h3> Patient Information</h3></th>
    <tr>
	<div>
        <td><form:label path="lastName">Last Name: </form:label> <mandatory>  *</mandatory></td>
        <td><form:input id="lastNameTxt" path="lastName"/></td>
	</tr>
	<tr>
        <td><form:label path="firstName">First Name: </form:label><mandatory>  *</mandatory></td>
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
	<td colspan="2" align="center"> <div>
        <form:button id="findPatient" name="findPatient">Find Patient</form:button>
    </div></td>

	</tr>
	</table>
</form:form>
<div id="header" align="center" class="header">
    <h3><span>Patient Locator Search Results</span></h3>
</div>
<table border="1" align="center" cellpadding="5">
    <tr>
        <th><text>Name</text></th>
        <th><text>DOB</text></th>
        <th><text>Gender</text></th>
        <th><text>Location</text></th>
        <th><text>MRN</text></th>
        <th><text>Encounter</text></th>
    </tr>
    <c:forEach items="${hPatientList}" var="patient" varStatus="status">
        <tr>
            <td width="130"><text>${patient.lastName}, ${patient.firstName}</text></td>
            <td width="150"><text>${patient.birthdate}</text></td>
            <td width="100"><text>
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
            <td width="100"><text>${patient.MPINo}</text></td>
            <td width="100"><text>
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