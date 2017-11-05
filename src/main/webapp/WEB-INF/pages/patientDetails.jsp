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

    .linkColor {
        color: white;
    }

</style>

<%@include file="siteHeader.jsp" %>

<body>
<div class="content">
	<h3><a href="/emrms/encounterDetails" class="linkColor">+ Add an Encounter</a></h3>
    <h3>Patient Demographics</h3>
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
            <form:input path="mPINumber" disabled="true" style="color:grey;"/>
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
            <form:button type="submit" value="Submit">Update Patient</form:button>&nbsp;&nbsp;
            <button type="button" name="back" onclick="history.back()">&laquo; Previous</button>
        </div>

        <c:if test="${not empty validationErrors}">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header" align="left">
                        <span class="close">&times;</span>
                        <p style="font-size:18px;">Please fix following errors before proceeding with patient
                            registration</p>
                    </div>
                    <div class="modal-body" align="left">
                        <c:forEach var="validationError" items="${validationErrors}" varStatus="loop">
                            <p style="color:red;">${validationError}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
        
        <form:hidden path="objectID" />

    </form:form>

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
</div>

<%@include file="siteFooter.jsp" %>

</body>
</html>