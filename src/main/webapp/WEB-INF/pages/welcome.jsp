<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Welcome</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
    <style>
        <%@include file="/css/formCommon.css"%>

        table, th, td {
            background: black;
            color: white;
            font-family: 'latoregular';
            font-size: 14px;
            border-collapse: collapse;
            padding: 15px;
            text-align: left;
            border-bottom: 2pt solid white;
            border-top: 2pt solid white;
        }
    </style>
</head>

<%@include file="siteHeader.jsp" %>

<body class="body" align="left">
<div class="content">
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
                <td width="130"><a
                        href="http://www.psu.edu">${physicianCensus.lastName}, ${physicianCensus.firstName}</a>
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

<%@include file="siteFooter.jsp" %>

</body>
</html>