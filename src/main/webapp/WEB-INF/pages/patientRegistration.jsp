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
		mandatory{
			color:red;
		}
		
    </style>
	<script type="text/javascript">
		function validateInput(){
				var lastName = document.getElementById("lastNameTxt").value;
				var firstName = document.getElementById("firstNameTxt").value;
				var cellphone = document.getElementById("cellphoneTxt").value;
				var email = document.getElementById("emailTxt").value;
				if(email.indexOf('@') < 0 && email != ""){
					alert("Invalid email format");
					validate = false;
				}
				var validate = null;
				if(lastName == "" || lastName == null || firstName == "" || firstName == null || cellphone == "" || cellphone == null){
					alert("Please enter values in mandatory fields marked by *");
					validate = false;
				} else{
				    validate = true;
				}
				return validate;
		}
	</script>
</head>

<div id="header">
    <h1><a href="/emrms/home">EMRMS<span>Electronic Medical Record Management System</span></a></h1>
    <ul id="navigation">
        <li class="current">
            <a href="/emrms/home">Home</a>
        </li>
    </ul>
</div>

<body align="center">
<h3> Patient Registration</h3>
<form:form id="addPatientForm" method="post" action="addPatient" modelAttribute="patient" onsubmit="return validateInput()">
   
	<table border="1" cellpadding="5" align="center">
	<th> <h3> Patient Information</h3></th>
	<tr>
    <div>
		<td><form:label path="name.first">First Name: <mandatory>*</mandatory></form:label></td>
        <td><form:input path="name.first" id="firstNameTxt"/></td>
        <td><form:label path="name.last">Last Name: <mandatory>*</mandatory></form:label></td>
        <td><form:input path="name.last" id="lastNameTxt"/></td>
    </div>
	</tr>
	<tr>
    <div>
        <td><form:label path="name.middle">Middle Name: </form:label></td>
        <td><form:input path="name.middle"/></td>
        <td><form:label path="gender">Gender: </form:label></td>
        <td><form:select path="gender">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
			</form:select></td>
    </div>
	</tr>
	<tr>
    <div>
        <td><form:label path="birthDate">Birth Date: </form:label></td>
        <td><form:input type="date" path="birthDate"/></td>
    </div>
	</tr>
    <th><h3> Address</h3></th>
	<tr>
    <div>
        <td><form:label path="address.line1">Street Address: </form:label></td>
        <td><form:input path="address.line1"/></td>
    </div>
	</tr>
	<tr>
    <div>
        <td><form:label path="address.city">City/State/Zip: </form:label></td>
        <td><form:input path="address.city"/></td>
        <td><form:input path="address.state"/></td>
        <td><form:input path="address.zip"/></td>
    </div>
	</tr>
	<tr>
    <div>
        <td><form:label path="cellPhone.number">Cell Phone: <mandatory>*</mandatory></form:label></td>
        <td><form:input path="cellPhone.number" id = "cellphoneTxt"/></td>
        <td><form:label path="homePhone.number">Home Phone: </form:label></td>
        <td><form:input path="homePhone.number"/></td>
    </div>
	</tr>
	<tr>
    <div>
        <td><form:label path="email">Email: </form:label></td>
        <td><form:input path="email" id="emailTxt"/></td>
    </div>
	</tr>
    </div>
    <th><h3> Miscellaneous</h3></th>
    <tr><div>
        <td><form:label path="organDonor">Organ Donor: </form:label></td>
        <td><form:select path="organDonor">
         <form:option value="true" label="Yes"/>
          <form:option value="false" label="No"/>
         </form:select></td>
    </div>
	</tr>
    <br>
	</table>
	<br/>
    <div>
        <form:button type="submit" value="Submit">Add Patient</form:button>
    </div>
</form:form>
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