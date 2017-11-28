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
    <c:if test="${empty hCensusList}">
        <h4 style="color:red;text-align: center;">No active patient records founds!</h4>
    </c:if>
    <table cellpadding="10" border="1" align="center">
        <tr>
            <th>Name</th>
            <th>DOB</th>
            <th>Gender</th>
            <th>Location</th>
            <th>MRN</th>
            <th>Encounter Status</th>
        </tr>
        <c:forEach items="${hCensusList}" var="physicianCensus" varStatus="status">
            <tr>
                <td width="130">
                    <form name="submitForm${physicianCensus.patientObjectid}" method="POST" action="/emrms/patientDetails">
                        <input type="hidden" name="patientObjectid" value="${physicianCensus.patientObjectid}"/>
                        <a href="javascript:document.submitForm${physicianCensus.patientObjectid}.submit()">${physicianCensus.lastName}, ${physicianCensus.firstName}</a>
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
                <td width="100">${physicianCensus.encounterLocationName}</td>
                <td width="100">${physicianCensus.MPINo}</td>
                <td width="100" style="white-space: nowrap;">
                    <form name="submitCharting${physicianCensus.patientObjectid}" method="GET" action="/emrms/charting">
                        <text>
                            <c:choose>
                                <c:when test="${physicianCensus.encStatus == '1'}">
                                    Active
                                    <input type="hidden" name="patientObjectid" value="${physicianCensus.patientObjectid}"/>
                                    <a href="javascript:document.submitCharting${physicianCensus.patientObjectid}.submit()">
                                        <img src="https://www.webpt.com/sites/default/files/images/icon-documentation.png"
                                             alt="Charting"
                                             style="width: 25px; height:auto; border:0; background-color: white;"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    Not Active
                                </c:otherwise>
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