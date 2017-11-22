<form:form id="addEncounterForm" method="post" action="addEncounter" modelAttribute="encounter"
           onsubmit="return validateInput()">
    <div style="display: flex;">
        <div class="flexColumn">
            <form:label cssClass="leftLabel" id="encounterTypeLabel" path="encounterType">Encounter Type:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="encStartDateTimeLabel" path="encStartDateTime">Encounter Start Date:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="encLocationNameLabel" path="encLocationName">Clinic:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="encStatusLabel" path="encStatus">Encounter Status:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="encounterIDLabel" path="encounterID">Encounter ID:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="encounterReasonLabel" path="encounterReason">Chief Complaint:
                <mandatory>*</mandatory>
            </form:label>
            <form:label cssClass="leftLabel" id="bedNameLabel" path="bedName">Attending Physician:
            	<mandatory>*</mandatory>
            </form:label>
        </div>

        <div class="flexColumn">
            <form:select cssClass="rightInput" id="encounterTypeInput" path="encounterType">
                <form:option value="Outpatient" label="Outpatient"/>
                <form:option value="Inpatient" label="Inpatient"/>
            </form:select>
            <form:input cssClass="rightInput" id="encStartDateTimeInput" type="date" path="encStartDateTime"/>
            <form:select cssClass="rightInput" path="encounterLocation_ObjectID" items="${patientLocations}" />
            <form:select cssClass="rightInput" id="encStatusInput" path="encStatus">
                <form:option value="1" label="Active"/>
                <form:option value="2" label="Close"/>
            </form:select>
            <form:input cssClass="rightInput" id="encounterIDInput" path="encounterID"/>
            <form:input cssClass="rightInput" id="encounterReasonInput" path="encounterReason"/>
            <form:input cssClass="rightInput" id="attendingPhysicianInput" path="attendingPhysician"/>
        </div>
    </div>

    <br/>
    <br/>

    <div>
        <form:button type="submit" value="Submit">Save Encounter</form:button>&nbsp;&nbsp;
        <button type="button" name="back" onclick="history.back()">&laquo; Previous</button>
    </div>
    
    <br/>
    <br/>
    
    <c:if test="${!empty encounterList}">
        <div>
	    	<table align="left">
		        <tr>
		            <th>Encounter Type</th>
		            <th>Encounter Start Date</th>
		            <th>Clinic</th>
		            <th>Encounter Status</th>
		            <th>Encounter ID</th>
		            <th>Chief Complaint</th>
		            <th>Attending Physician</th>
		            <th>Action</th>
		        </tr>
		        <c:forEach items="${encounterList}" var="encounterDetail" varStatus="encounterStatus">
		            <tr>
		                <td width="150">${encounterDetail.encounterType}</td>
		                <td width="150">${encounterDetail.encStartDateTime}</td>
		                <td width="150">${encounterDetail.encLocationName}</td>              
		                <td width="150">
		                    <c:choose>
		                        <c:when test="${encounterDetail.encStatus == '1'}">
		                            Active
		                        </c:when>
		                        <c:otherwise>
		                            Closed
		                        </c:otherwise>
		                    </c:choose>
	               		</td>	                
		                <td width="150">${encounterDetail.encounterID}</td>
		                <td width="200">${encounterDetail.encounterReason}</td>
		                <td width="150">${encounterDetail.attendingPhysician}</td>
		                <td width="100"><form:button type="submit" value="Submit">Revise</form:button></td>
		            </tr>
	        	</c:forEach>
	    	</table>
	    </div>
    </c:if>

    <!-- This div is to fix the footer overlapping issue -->
    <div>
        <input style="visibility:hidden;" type="button">
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
        window.alert("Encounter has been saved Successfully");
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