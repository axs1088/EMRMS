<%@ include file="/WEB-INF/pages/subpages/include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Encounter</title>
    
    <script src="http://code.jquery.com/jquery-1.7.js" type="text/javascript"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<STYLE TYPE="text/css" media="all">
		.ui-autocomplete { 
		    position: absolute; 
		    cursor: default; 
		    height: 200px; 
		    overflow-y: scroll; 
		    overflow-x: hidden;}
	</STYLE>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $("input#attendingPhysician").autocomplete({
		        width: 300,
		        max: 10,
		        delay: 100,
		        minLength: 1,
		        autoFocus: true,
		        cacheLength: 1,
		        scroll: true,
		        highlight: false,
		        source: function(request, response) {
		            $.ajax({
		                url: "physiciandetails",
		                dataType: "json",
		                data: {
		                	 searchString: $('#attendingPhysician').val(),
		                },
		                success: function(data, textStatus, jqXHR) {
		                    console.log( data);
		                    var items = data;
		                    response(items);
		                },
		                error: function(jqXHR, textStatus, errorThrown){
		                     console.log( textStatus);
		                }
		            });
		        }
		
		    });
		});
	   
	</script>

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

<%@include file="subpages/siteHeader.jsp" %>

<body onload="openDefaultTab()">
<div class="mainContent">
    <h3>Add an Encounter</h3>
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
        
        tr:nth-of-type(odd) {
      		background-color:#e6e6e6;
    	}
    </style>

    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'EncounterDetails')" id="defaultOpen">Encounter Details
        </button>
        <button class="tablinks" onclick="openTab(event, 'Transfer')">Transfer</button>
        <button class="tablinks" onclick="openTab(event, 'Checkout')">Check-Out</button>
    </div>

    <div id="EncounterDetails" class="tabcontent">
        <%@include file="subpages/encounterDetails.jsp" %>
    </div>

    <div id="Transfer" class="tabcontent">
        <%@include file="subpages/encounterTransfer.jsp" %>
    </div>

    <div id="Checkout" class="tabcontent">
        <%@include file="subpages/encounterCheckout.jsp" %>
    </div>
</div>
</body>

<%@include file="subpages/siteFooter.jsp" %>
</html>