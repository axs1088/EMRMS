<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Charting</title>

    <script type="application/javascript">
        function openTab(evt, tabName) {
            // Declare all variables
            var i, tabcontent, tablinks;

            // Get all elements with class="tabcontent" and hide them
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }

            // Get all elements with class="tablinks" and remove the class "active"
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }

            // Show the current tab, and add an "active" class to the button that opened the tab
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }

        function openDefaultTab() {
            // Get the element with id="defaultOpen" and click on it
            document.getElementById("defaultOpen").click();
        }
    </script>
</head>

<style>
    <%@include file="/css/emrms.css"%>
</style>

<%@include file="subpages/siteHeader.jsp" %>
<body onload="openDefaultTab()">
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
        <button class="tablinks" onclick="openTab(event, 'Allergies')" id="defaultOpen">Allergies</button>
        <button class="tablinks" onclick="openTab(event, 'Assessments')">Assessments</button>
        <button class="tablinks" onclick="openTab(event, 'Diagnoses')">Diagnoses</button>
        <button class="tablinks" onclick="openTab(event, 'Problems')">Problems</button>
    </div>

    <div id="Allergies" class="tabcontent">
        <%@include file="subpages/chartingAllergies.jsp" %>
    </div>

    <div id="Assessments" class="tabcontent">
        <%@include file="subpages/chartingAssessments.jsp" %>
    </div>

    <div id="Diagnoses" class="tabcontent">
        <%@include file="subpages/chartingDiagnoses.jsp" %>
    </div>

    <div id="Problems" class="tabcontent">
        <%@include file="subpages/chartingProblems.jsp" %>
    </div>
</div>
</body>

<%@include file="subpages/siteFooter.jsp" %>

</html>