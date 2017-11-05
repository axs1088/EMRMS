<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lookup Policies</title>
    <style>
        <%@include file="/css/emrms.css"%>
    </style>
</head>

<%@include file="subpages/siteHeader.jsp" %>

<div class="mainContent">
    <h3>Policies</h3>
    <form:form id="patientPoliciesForm" method="post" action="policyProcess" modelAttribute="policy">
        <table border="1" cellpadding="5" align="center">
            <th colspan="2"><h3>Search</h3></th>
            <tr>
                <div>
                    <td><form:label path="startDate">Start Date: </form:label>
                        <mandatory> *</mandatory>
                    </td>
                    <td><form:input type="date" id="startDateTxt" path="startDate"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><form:label path="endDate">End Date: </form:label>
                        <mandatory> *</mandatory>
                    </td>
                    <td><form:input type="date" id="endDateTxt" path="endDate"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><form:label id="endDateTxt" path="policyName">Policy Name: </form:label>
                    </td>
                    <td><form:label id="endDateTxt" path="policyName"/></td>
                </div>
            </tr>
            <tr>
                <div>
                    <td><input type="submit" value="Search"></td>
                </div>
            </tr>
        </table>
    </form:form>

    <div id="header" align="center" class="header">
        <h3><span>Policy Search Results</span></h3>
    </div>
</div>

<%@include file="subpages/siteFooter.jsp" %>

</html>