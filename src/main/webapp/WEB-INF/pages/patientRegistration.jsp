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
        <%@include file="/css/formCommon.css"%>
        
        #addressInput {
            width: 300px;
        }

        #zipInput {
            width: 75px;
        }
    </style>
    <script type="text/javascript">
        function validateInput() {
            var lastName = document.getElementById("lastNameTxt").value;
            var firstName = document.getElementById("firstNameTxt").value;
            var birthDate = document.getElementById("birthDateTxt").value;
            var email = document.getElementById("emailTxt").value;
            var gender = document.getElementById("genderTxt").value;
            var validate = true;

            if (email.indexOf('@') < 0 && email != "") {
                alert("Invalid email format");
                validate = false;
            }

            if (lastName == "" || lastName == null
                || firstName == "" || firstName == null
                || birthDate == "" || birthDate == null
                || gender == "" || gender == null) {
                alert("Please enter values in mandatory fields marked by *");
                validate = false;
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

<body>
<h3>Patient Registration</h3>
<form:form id="addPatientForm" method="post" action="addPatient" modelAttribute="patient"
           onsubmit="return validateInput()">
    <h4> Patient Information</h4>

    <div>
        <form:label path="name.first">First Name:
            <mandatory>*</mandatory>
        </form:label>
        <form:input path="name.first" id="firstNameTxt"/>
        <form:label path="name.last">Last Name:
            <mandatory>*</mandatory>
        </form:label>
        <form:input path="name.last" id="lastNameTxt"/>
    </div>

    <div>
        <form:label path="name.middle">Middle Name: </form:label>
        <form:input path="name.middle"/>
        <form:label path="gender">Gender:
            <mandatory>*</mandatory>
        </form:label>
        <form:select path="gender" id="genderTxt">
            <form:option value="1" label="Male"/>
            <form:option value="2" label="Female"/>
            <form:option value="3" label="Other"/>
        </form:select>
    </div>

    <div>
        <form:label path="birthDate">Birth Date:
            <mandatory>*</mandatory>
        </form:label>
        <form:input type="date" path="birthDate" id="birthDateTxt"/>
    </div>

    <h4> Address</h4>

    <div>
        <form:label path="address.line1">Street Address: </form:label>
        <form:input id="addressInput" path="address.line1"/>
    </div>

    <div>
        <form:label path="address.city">City/State/Zip: </form:label>
        <form:input path="address.city"/>
        <form:select path="address.state">
            <form:option value="Alabama"/>
            <form:option value="Alaska"/>
            <form:option value="Arizona"/>
            <form:option value="Arkansas"/>
            <form:option value="California"/>
            <form:option value="Colorado"/>
            <form:option value="Connecticut"/>
            <form:option value="Delaware"/>
            <form:option value="Florida"/>
            <form:option value="Georgia"/>
            <form:option value="Hawaii"/>
            <form:option value="Idaho"/>
            <form:option value="Illinois"/>
            <form:option value="Indiana"/>
            <form:option value="Iowa"/>
            <form:option value="Kansas"/>
            <form:option value="Kentucky"/>
            <form:option value="Louisiana"/>
            <form:option value="Maine"/>
            <form:option value="Maryland"/>
            <form:option value="Massachusetts"/>
            <form:option value="Michigan"/>
            <form:option value="Minnesota"/>
            <form:option value="Mississippi"/>
            <form:option value="Missouri"/>
            <form:option value="Montana"/>
            <form:option value="Nebraska"/>
            <form:option value="Nevada"/>
            <form:option value="New Hampshire"/>
            <form:option value="New Jersey"/>
            <form:option value="New Mexico"/>
            <form:option value="New York"/>
            <form:option value="North Carolina"/>
            <form:option value="North Dakota"/>
            <form:option value="Ohio"/>
            <form:option value="Oklahoma"/>
            <form:option value="Oregon"/>
            <form:option value="Pennsylvania"/>
            <form:option value="Rhode Island"/>
            <form:option value="South Carolina"/>
            <form:option value="South Dakota"/>
            <form:option value="Tennessee"/>
            <form:option value="Texas"/>
            <form:option value="Utah"/>
            <form:option value="Vermont"/>
            <form:option value="Virginia"/>
            <form:option value="Washington"/>
            <form:option value="West Virginia"/>
            <form:option value="Wisconsin"/>
            <form:option value="Wyoming"/>
        </form:select>
        <form:input id="zipInput" path="address.zip"/>
    </div>

    <div>
        <form:label path="address.line1">Mailing Address: </form:label>
        <form:checkbox path="address.mailingAddrSameAsHomeAddr" value="1" label="Same as home"/>
    </div>

    <div>
        <form:label path="cellPhone.number">Cell Phone: </form:label>
        <form:input path="cellPhone.number" id="cellphoneTxt"/>
        <form:label path="homePhone.number">Home Phone: </form:label>
        <form:input path="homePhone.number"/>
    </div>

    <div>
        <form:label path="email">Email: </form:label>
        <form:input path="email" id="emailTxt"/>
    </div>

    <h4> Miscellaneous</h4>
    <div>
        <form:label path="mPINumber">MPI Number: </form:label>
        <form:input path="mPINumber"/>
    </div>
    <div>
        <form:label path="organDonor">Organ Donor: </form:label>
        <form:select path="organDonor">
            <form:option value="true" label="Yes"/>
            <form:option value="false" label="No"/>
        </form:select>
    </div>
    <br>
    <br/>
    <div>
        <form:button type="submit" value="Submit">Add Patient</form:button>
    </div>
</form:form>

<script type="text/javascript">
    var saveMessage = "${message}".toString();
    var m = "Save Successful";
    if (saveMessage.valueOf() == m.valueOf()) {
        window.alert("Save Successful");
    } //end of if
</script>

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