<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Login</title>
    <style>
        table, th {
            background: #F8F8FF;
            border-collapse: collapse;
            padding: 10px;
            text-align: left;
        }

        table, td {
            padding: 10px;
            text-align: left;
        }
    </style>
    <script type="text/javascript">
        function validateData() {
        }
    </script>
</head>

<div id="header">
    <h1><a href="/emrms/home">EMRMS<span>Electronic Medical Record Management System</span></a></h1>
    <ul id="navigation">
        <li class="current">
            <a href="/emrms/home">Home</a>
        </li>
    </ul>
</div>

<body align="center" class="body">
<div class="content">
    <form:form id="loginForm" modelAttribute="user" action="loginProcess" method="post">
        <table align="center">
            <tr>
                <td class="w3-container w3-green">Login</td>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="username">Username: </form:label>
                </td>
                <td>
                    <form:input path="username" name="username" id="username"/>
                </td>
            </tr>
            </tr>
            <tr>
                <td>
                    <form:label path="password">Password:</form:label>
                </td>
                <td>
                    <form:password path="password" name="password" id="password"/>
                </td>
            </tr>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <form:button id="login" name="login" onclick="validateData()">Login</form:button>
                </td>
            </tr>
            <tr></tr>
        </table>
    </form:form>
    <table align="center">
        <tr>
            <td style="font-style: italic; color: red;">${message}</td>
        </tr>
    </table>
</div>
<%@include file="siteFooter.jsp" %>

</body>
</html>