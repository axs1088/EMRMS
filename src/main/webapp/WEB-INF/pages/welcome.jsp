<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Welcome</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
</head>

<%@include file="subpages/siteHeader.jsp" %>

<div class="mainContent">
    <h3 style="text-align: center"><b>Patient Census</b></h3>
    <table cellpadding="10" border="1" align="center">
        <tr style=>
            <th>Name</th>
            <th>DOB</th>
            <th>Gender</th>
            <th>Location</th>
            <th>MRN</th>
            <th>Encounter</th>
        </tr>
        <c:forEach items="${hCensusList}" var="physicianCensus" varStatus="status">
            <tr>
                <td width="130">
                    <form name="submitForm${physicianCensus.hPatientID}" method="POST" action="/emrms/patientDetails">
                        <input type="hidden" name="hPatientID" value="${physicianCensus.hPatientID}"/>
                        <a href="javascript:document.submitForm${physicianCensus.hPatientID}.submit()">${physicianCensus.lastName}, ${physicianCensus.firstName}</a>
                    </form>
                </td>
                <td width="150">${physicianCensus.birthdate}</td>
                <td width="150">
                    <c:choose>
                        <c:when test="${physicianCensus.gender == '1'}">
                            Male
                        </c:when>
                        <c:otherwise>
                            Female
                        </c:otherwise>
                    </c:choose>
                </td>
                <td width="100"></td>
                <td width="100">${physicianCensus.MPINo}</td>
                <td width="100">
                    <c:choose>
                        <c:when test="${physicianCensus.encStatus == '1'}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Not Active
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="subpages/siteFooter.jsp" %>

</html>