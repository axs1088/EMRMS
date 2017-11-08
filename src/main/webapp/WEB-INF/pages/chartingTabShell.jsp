<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Charting</title>

    <script type="application/javascript">
        function openTab(tabName) {
            var i;
            document.getElementById("activeTabField").setAttribute("value", tabName);

            var tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            document.getElementById(tabName + "Content").style.display = "block";

            var tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName + "Tab").className += " active";
        }

        function openActiveTab() {
            openTab(document.getElementById("activeTabField").getAttribute("value"));
        }
    </script>
</head>

<style>
    <%@include file="/css/emrms.css"%>
</style>

<%@include file="subpages/siteHeader.jsp" %>
<body onload="openActiveTab()">

<form:form modelAttribute="activeTabs">
    <form:hidden id="activeTabField" path="charting"></form:hidden>
</form:form>

<div class="mainContent">
    <h3>Charting</h3>
    <style>
        div.tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        div.tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
        }

        div.tab button:hover {
            background-color: #ddd;
        }

        div.tab button.active {
            background-color: #ccc;
        }

        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>

    <div class="tab">
        <button class="tablinks" id="allergiesTab" onclick="openTab('allergies')">Allergies</button>
        <button class="tablinks" id="assessmentsTab" onclick="openTab('assessments')">Assessments</button>
        <button class="tablinks" id="diagnosesTab" onclick="openTab('diagnoses')">Diagnoses</button>
        <button class="tablinks" id="problemsTab" onclick="openTab('problems')">Problems</button>
    </div>

    <div id="allergiesContent" class="tabcontent">
        <%@include file="subpages/chartingAllergies.jsp" %>
    </div>

    <div id="assessmentsContent" class="tabcontent">
        <%@include file="subpages/chartingAssessments.jsp" %>
    </div>

    <div id="diagnosesContent" class="tabcontent">
        <%@include file="subpages/chartingDiagnoses.jsp" %>
    </div>

    <div id="problemsContent" class="tabcontent">
        <%@include file="subpages/chartingProblems.jsp" %>
    </div>
</div>
</body>

<%@include file="subpages/siteFooter.jsp" %>

</html>