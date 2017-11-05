<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<header>
    <label style="font-weight: bold; font-size: 300%;">EMRMS</label>
    <h3>Electronic Medical Record Management System</h3>
</header>

<body align="center" class="body">
<div class="mainContent">
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
</body>

<%@include file="subpages/siteFooter.jsp" %>

</html>