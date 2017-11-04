<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lookup Policies</title>
    <style>
        <%@include file="/css/formCommon.css"%>
    </style>
</head>

<%@include file="siteHeader.jsp" %>

<body>
<div class="content">
    <form:form id="patientPoliciesForm" method="post" action="policyProcess" modelAttribute="policy">
    <table border="1" cellpadding="5" align="center">
        <th colspan="2"><h3> Policy Information</h3></th>
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
<%@include file="siteFooter.jsp" %>

</body>
</html>