<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
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
        
        function validateInput() {
            var encounterType = document.getElementById("encounterTypeInput").value;
            var encStartDateTime = document.getElementById("encStartDateTimeInput").value;
            var encLocationName = document.getElementById("encLocationNameInput").value;
            var encStatus = document.getElementById("encStatusInput").value;
            var encounterID = document.getElementById("encounterIDInput").value;
            var encounterReason = document.getElementById("encounterReasonInput").value;
            var validate = true;

            if (isBlank(encounterType) || isBlank(encStartDateTime) || isBlank(encLocationName) || isBlank(encStatus) 
            		|| isBlank(encounterID) || isBlank(encounterReason)) {
                alert("Please enter values in mandatory fields marked by *");
                validate = false;
            }

            return validate;
        }
        
        function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }
        
    </script>
</head>


<style>
    <%@include file="/css/formCommon.css"%>
</style>

<%@include file="siteHeader.jsp" %>

<body onload="openDefaultTab()">
<div class="content">
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
        <button class="tablinks" onclick="openTab(event, 'EncounterDetails')" id="defaultOpen">Encounter Details</button>
        <button class="tablinks" onclick="openTab(event, 'Transfer')">Transfer</button>
        <button class="tablinks" onclick="openTab(event, 'Checkout')">Check-Out</button>
    </div>

    <div id="EncounterDetails" class="tabcontent">
        <%@include file="encounterDetails.jsp" %>
    </div>

    <div id="Transfer" class="tabcontent">
        <%@include file="encounterTransfer.jsp" %>
    </div>

    <div id="Checkout" class="tabcontent">
        <%@include file="encounterCheckout.jsp" %>
    </div>

</div>

<%@include file="siteFooter.jsp" %>

</body>
</html>