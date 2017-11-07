<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
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

<%@include file="subpages/siteHeader.jsp" %>

<div class="mainContent">
    <h3>Add Patient</h3>
    <form:form id="addPatientForm" method="post" action="addPatient" modelAttribute="patient"
               onsubmit="return validateInput()">
        <%@ include file="subpages/patientForm.jsp" %>
        <br>
        <br/>
        <div>
            <form:button type="submit" value="Submit">Save Patient</form:button>&nbsp;&nbsp;
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