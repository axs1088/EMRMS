<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

            if (isBlank(lastName) || isBlank(firstName) || isBlank(birthDate) || isBlank(gender)) {
                alert("Please enter values in mandatory fields marked by *");
                validate = false;
            }

            return validate;
        }

        function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }

    </script>
</head>

<style>
    <%@include file="/css/emrms.css"%>

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
</style>

<%@include file="subpages/siteHeader.jsp" %>

<div class="mainContent" onload="openDefaultTab()">
    <h3>Patient Demographics</h3>
    <form action="encounterDetails">
        <button type="submit">Add an Encounter</button>
    </form>
    <form:form id="addPatientForm" method="post" action="addPatient" modelAttribute="patient"
               onsubmit="return validateInput()">
        <%@include file="subpages/patientForm.jsp" %>

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

        <form:hidden path="objectID"/>
        <form:hidden path="personId"/>

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

<%@include file="subpages/siteFooter.jsp" %>

</html>