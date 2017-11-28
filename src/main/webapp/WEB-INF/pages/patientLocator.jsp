<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Locate Patient</title>
    <script type="text/javascript">
        function validateInput() {
            var lastName = document.getElementById("lastNameTxt").value;
            var firstName = document.getElementById("firstNameTxt").value;
            if (isBlank(lastName) || isBlank(firstName)) {
                alert("Please fill in mandatory fields marked by *");
                return false;
            } else {
                return true;
            }
        }

        function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }
    </script>
    <style>
        <%@include file="/css/emrms.css"%>
    </style>
</head>

<%@include file="subpages/siteHeader.jsp" %>

<div class="mainContent">
    <h3>Patient Locator</h3>
    <form:form id="patientLocatorForm" method="post" action="patientLocatorProcess" modelAttribute="census">
        <table border="1" cellpadding="5" align="center">
            <th colspan="2"><h3>Search</h3></th>
            <tr>
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
            <tr>
                <td><form:label path="gender">Gender: </form:label></td>
                <td><form:select path="gender">
                    <form:option value="1" label="Male"/>
                    <form:option value="2" label="Female"/>
                    <form:option value="3" label="Other"/>
                </form:select></td>
            </tr>
            <br>
            <tr>
                <td colspan="2" align="center">
                    <div>
                        <form:button id="findPatient" name="findPatient"
                                     onclick="return validateInput()">Find Patient</form:button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <form:button id="addPatient" name="addPatient">Add Patient</form:button>
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
                <text>Encounter Status</text>
            </th>
        </tr>
        <c:forEach items="${hPatientList}" var="patientlist" varStatus="status">
            <tr>
                <td width="130">
                    <form name="submitForm${patientlist.patientObjectid}" method="POST" action="/emrms/patientDetails">
                        <input type="hidden" name="patientObjectid" value="${patientlist.patientObjectid}"/>
                        <a href="javascript:document.submitForm${patientlist.patientObjectid}.submit()">${patientlist.lastName}, ${patientlist.firstName}</a>
                    </form>
                </td>
                <td width="150">
                    <text>${patientlist.birthdate}</text>
                </td>
                <td width="100">
                    <text>
                        <c:choose>
                            <c:when test="${patientlist.gender == '1'}">
                                Male
                            </c:when>
                            <c:otherwise>
                                Female
                            </c:otherwise>
                        </c:choose>
                    </text>
                </td>
                <td width="100">${patientlist.encounterLocationName}</td>
                <td width="100">
                    <text>${patientlist.MPINo}</text>
                </td>
                <td width="100" style="white-space: nowrap;">
                    <form name="submitCharting${patientlist.patientObjectid}" method="GET" action="/emrms/charting">
                        <text>
                            <c:choose>
                                <c:when test="${patientlist.encStatus == '1'}">
                                    Active
                                    <input type="hidden" name="patientObjectid" value="${patientlist.patientObjectid}"/>
                                    <a href="javascript:document.submitCharting${patientlist.patientObjectid}.submit()">
                                        <img src="https://www.webpt.com/sites/default/files/images/icon-documentation.png"
                                             alt="Charting"
                                             style="width: 25px; height:auto; border:0; background-color: white;"/>
                                    </a>
                                </c:when>
                                <c:when test="${patientlist.encStatus == '2'}">
                                    Closed
                                </c:when>
                                <c:when test="${patientlist.encStatus == '2'}">
                                    Scheduled
                                </c:when>
                            </c:choose>
                        </text>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="subpages/siteFooter.jsp" %>

</html>