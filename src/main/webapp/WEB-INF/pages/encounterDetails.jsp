<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>

    <h3>Encounter Details</h3>
    <form:form id="addEncounterForm" method="post" action="addEncounter" modelAttribute="encounter"
               onsubmit="return validateInput()">
        <div>         
           	<style>
	            #encounterTypeLabel {
	                width: 150px;
	            }
	
	            #encounterTypeInput {
	                width: 150px;
	            }
	        </style>	        
	        <style>
	            #encStartDateTimeLabel {
	                width: 250px;
	            }
	
	            #encStartDateTimeInput {
	                width: 150px;
	            }
	        </style>        
            <form:label id="encounterTypeLabel" path="encounterType">Encounter Type:
                <mandatory>*</mandatory>
            </form:label>
            <form:select id="encounterTypeInput" path="encounterType">
                <form:option value="1" label="Outpatient"/>
                <form:option value="2" label="Inpatient"/>
                <form:option value="3" label="Other"/>
            </form:select>       
            <form:label id="encStartDateTimeLabel" path="encStartDateTime">Encounter Start Date:
                <mandatory>*</mandatory>
            </form:label>
            <form:input id="encStartDateTimeInput" type="date" path="encStartDateTime"/>           
        </div>

        <div>
       		<style>
	            #encLocationNameLabel {
	                width: 150px;
	            }
	
	            #encLocationNameInput {
	                width: 150px;
	            }
	        </style>
	        
	        <style>
	            #encStatusLabel {
	                width: 250px;
	            }
	
	            #encStatusInput {
	                width: 150px;
	            }
	        </style>  
            <form:label id="encLocationNameLabel" path="encLocationName">Clinic:
                <mandatory>*</mandatory>
            </form:label>
            <form:select id="encLocationNameInput" path="encLocationName">
                <form:option value="1" label="Exton Clinic"/>
                <form:option value="2" label="Paoli Clinic"/>
                <form:option value="3" label="Other"/>
            </form:select>
            <form:label id="encStatusLabel" path="encStatus">Encounter Status:
                <mandatory>*</mandatory>
            </form:label>
            <form:select id="encStatusInput" path="encStatus">
                <form:option value="1" label="Active"/>
            </form:select>
        </div>

        <div>
       		<style>
	            #encounterIDLabel {
	                width: 150px;
	            }
	
	            #encounterIDInput {
	                width: 150px;
	            }
	        </style>	        
	        <style>
	            #encounterReasonLabel {
	                width: 250px;
	            }
	
	            #encounterReasonInput {
	                width: 150px;
	            }
	        </style> 
            <form:label id="encounterIDLabel" path="encounterID">Encounter ID:
                <mandatory>*</mandatory>
            </form:label>
            <form:input id="encounterIDInput" path="encounterID"/>
			<form:label id="encounterReasonLabel" path="encounterReason">Chief Complaint:
                <mandatory>*</mandatory>
            </form:label>
            <form:input id="encounterReasonInput" path="encounterReason"/>
        </div>
        
        <div>
       		<style>
	            #bedNameLabel {
	                width: 150px;
	            }	
	            #bedNameInput {
	                width: 150px;
	            }
	        </style>
            <form:label id="bedNameLabel" path="bedName">Attending Physician: </form:label>
            <form:input id="bedNameInput" path="bedName"/>
        </div>
        
        <br>
        <br/>
        
        <div>
            <form:button type="submit" value="Submit">Save</form:button>
        </div>

        <c:if test="${not empty validationErrors}">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header" align="left">
                        <span class="close">&times;</span>
                        <p style="font-size:18px;">Please fix following errors before proceeding with save encounter</p>
                    </div>
                    <div class="modal-body" align="left">
                        <c:forEach var="validationError" items="${validationErrors}" varStatus="loop">
                            <p style="color:red;">${validationError}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>

    </form:form>

    <script type="text/javascript">
        //Execute below if there is success
        var saveSuccess = "${saveSuccess}";
        if (saveSuccess) {
            window.alert("Save Successful");
        }

        //Execute below if there is error on the page
        var errorOnPage = "${errorOnPage}";
        if (errorOnPage) {
            var modal = document.getElementById('myModal');
            var span = document.getElementsByClassName("close")[0];
            modal.style.display = "block";
            span.onclick = function () {
                modal.style.display = "none";
            }
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        }
    </script>