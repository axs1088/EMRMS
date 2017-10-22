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

        .error {
            color: red;
            font-weight: bold;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
            -webkit-animation-name: fadeIn; /* Fade in the background */
            -webkit-animation-duration: 0.4s;
            animation-name: fadeIn;
            animation-duration: 0.4s
        }

        /* Modal Content */
        .modal-content {
            position: fixed;
            bottom: 0;
            background-color: #fefefe;
            width: 800px;
            -webkit-animation-name: slideIn;
            -webkit-animation-duration: 0.4s;
            animation-name: slideIn;
            animation-duration: 0.4s
        }

        /* The Close Button */
        .close {
            color: white;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-header {
            padding: 2px 16px;
            background-color: #808080;
            color: white;
        }

        .modal-body {
            padding: 2px 16px;
        }

        /* Add Animation */
        @-webkit-keyframes slideIn {
            from {
                bottom: -300px;
                opacity: 0
            }
            to {
                bottom: 0;
                opacity: 1
            }
        }

        @keyframes slideIn {
            from {
                bottom: -300px;
                opacity: 0
            }
            to {
                bottom: 0;
                opacity: 1
            }
        }

        @-webkit-keyframes fadeIn {
            from {
                opacity: 0
            }
            to {
                opacity: 1
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0
            }
            to {
                opacity: 1
            }
        }

        .topcorner {
            position: absolute;
            top: 0;
            right: 0;
        }

        .linkColor {
            color: white;
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
</div>

<div class="topcorner">
    <a href="/emrms/loginProcess" class="linkColor">Home</a>&nbsp;&nbsp;
    <a href="/emrms/logout" class="linkColor">Logout</a>
</div>

<body>
<h3>Patient Registration</h3>
    <h4> Patient Information</h4>

    <div>
        <label>First Name: </label>
        <mandatory>*</mandatory>
        <input type="text" value="${firstName}">
		
		<label>Last Name: </label>
        <mandatory>*</mandatory>
        <input type="text" value="${lastName}">
		</br>
    </div>

    <div>
        <label>Middle Name:</label>
        &nbsp;&nbsp;&nbsp;<input type="text" value="${middleName}">
		<label> Gender <mandatory>*</mandatory></label>
		&nbsp;&nbsp;
        <select id="genderTxt" value="${gender}">
            <option value="1" label="Male"/>
            <option value="2" label="Female"/>
            <option value="3" label="Other"/>
        </select>
    </div>

    <div>
       <label>Birth Date: </label>
        <mandatory>*</mandatory>
        <input type="text" value="${dateOfBirth}">
		</br>
    </div>

    <h4> Address</h4>

    <div>
        <label>Street Address:</label>
        <input id="addressInput" type="text" value="${streetAddressLine1}"/>
		<br/>
    </div>

    <div>
        <label>City/State/Zip:</label>
        <input type="text" value="${city}"/>
        <select id="stateDropDown" value="${state}">
            <option value="Alabama"/>
            <option value="Alaska"/>
            <option value="Arizona"/>
            <option value="Arkansas"/>
            <option value="California"/>
            <option value="Colorado"/>
            <option value="Connecticut"/>
            <option value="Delaware"/>
            <option value="Florida"/>
            <option value="Georgia"/>
            <option value="Hawaii"/>
            <option value="Idaho"/>
            <option value="Illinois"/>
            <option value="Indiana"/>
            <option value="Iowa"/>
            <option value="Kansas"/>
            <option value="Kentucky"/>
            <option value="Louisiana"/>
            <option value="Maine"/>
            <option value="Maryland"/>
            <option value="Massachusetts"/>
            <option value="Michigan"/>
            <option value="Minnesota"/>
            <option value="Mississippi"/>
            <option value="Missouri"/>
            <option value="Montana"/>
            <option value="Nebraska"/>
            <option value="Nevada"/>
            <option value="New Hampshire"/>
            <option value="New Jersey"/>
            <option value="New Mexico"/>
            <option value="New York"/>
            <option value="North Carolina"/>
            <option value="North Dakota"/>
            <option value="Ohio"/>
            <option value="Oklahoma"/>
            <option value="Oregon"/>
            <option value="Pennsylvania"/>
            <option value="Rhode Island"/>
            <option value="South Carolina"/>
            <option value="South Dakota"/>
            <option value="Tennessee"/>
            <option value="Texas"/>
            <option value="Utah"/>
            <option value="Vermont"/>
            <option value="Virginia"/>
            <option value="Washington"/>
            <option value="West Virginia"/>
            <option value="Wisconsin"/>
            <option value="Wyoming"/>
        </select>
        <input id="zipInput" type="text" value="${zip}">
		<br/>
    </div>

    <div>
        <label>Mailing Address:</label>
        <checkbox value="1"/> <label>Same as home</label>
    </div>

    <div>
        <label>Cell Phone:</label>
        <input id="cellphoneTxt" vaue="${cellPhone}"/>
        <label>Home Phone: </label>
        <input type="text" value="${homePhone}"/>
		</br>
    </div>

    <div>
         <label>Email:</label>
        <input id="emailTxt" vaue="${email}"/>
        </br>
    </div>

    <h4> Miscellaneous</h4>
    <div>
         <label>MPI Number: </label>
        <input id="mpiTxt" vaue="${mpiNo}"/>
        </br>
    </div>
    <div>
        <label>Organ Donor: </label>
        <select value="${organDonor}">
            <option value="true" label="Yes"/>
            <option value="false" label="No"/>
        </select>
    </div>
    <br>
    <br/>
    <div>
        <a href="/emrms/patientLocator" class="linkColor">Back</a>&nbsp;&nbsp>
    </div>
<script type="text/javascript">
    //Execute below if there is success
    var saveSuccess = "${saveSuccess}";
    if (saveSuccess) {
        window.alert("Save Successful");
    }

    //Execute below if there is error on the page
    var errorOnPage = "${errorOnPage}";
    if (errorOnPage) {
        var modal = document.getElementById('myModal');
        var span = document.getElementsByClassName("close")[0];
        modal.style.display = "block";
        span.onclick = function () {
            modal.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
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