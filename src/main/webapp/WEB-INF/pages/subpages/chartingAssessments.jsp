<table id="assessmentTable" border="1" align="center" cellpadding="5" width="100%" style="input{ width:auto; }">
    <tr>
        <th width="10%" title="Unique encounter identifier">
            <text>Enc #</text>
        </th>
        <th width="14%" title="Encounter start date & time">
            <text>Enc Date / Time</text>
        </th>
        <th width="10%" title="Encounter type">
            <text>Enc Type</text>
        </th>
        <th width="10%" title="Assessment status">
            <text>Status</text>
        </th>
        <th width="10%" title="Temperature measured in degrees Fahrenheit">
            <text>Temperature<br>(F)</text>
        </th>
        <th width="10%" title="Pulse measured in beats per minute">
            <text>Pulse<br>(bpm)</text>
        </th>
        <th width="10%" title="Blood pressure measured in systolic over diastolic">
            <text>Blood Pressure<br>(s/d)</text>
        </th>
        <th width="10%" title="Height measured in feet">
            <text>Height<br>(ft")</text>
        </th>
        <th width="10%" title="Weight measured in pounds">
            <text>Weight<br>(lbs)</text>
        </th>
        <th width="6%" title="Add or remove an assessment">
            <text>Action</text>
        </th>
    </tr>

    <c:set var="showAddActionVar" value="${showAddAction}"/>
    <c:if test="${showAddActionVar eq true}">
        <tr>
            <form:form modelAttribute="currentEncounter">
                <td width="10%">
                    <form:input size="8" disabled="true" path="encounterID"/>
                </td>
                <td width="14%">
                    <form:input size="18" disabled="true" path="encStartDateTime"/>
                </td>
                <td width="10%">
                    <form:input size="8" disabled="true" path="encounterType"/>
                </td>
            </form:form>
            <form:form id="addAssessmentForm" method="post" action="addAssessment"
                       modelAttribute="newAssessment" name="addAssessmentForm">
                <td width="10%">
                    <form:input size="8" path="status"/>
                </td>
                <td width="10%">
                    <form:input size="8" path="temperature"/>
                </td>
                <td width="10%">
                    <form:input size="8" path="pulse"/>
                </td>
                <td width="10%" style="white-space: nowrap">
                    <form:input size="4" path="systolicBP"/>
                    <label>/</label>
                    <form:input size="4" path="dystolicBP"/>
                </td>
                <td width="10%">
                    <form:input size="8" path="height"/>
                </td>
                <td width="10%">
                    <form:input size="8" path="weight"/>
                </td>
                <td width="6%">
                    <a href="javascript:document.addAssessmentForm.submit()">Add</a>
                </td>
            </form:form>
        </tr>
    </c:if>

    <c:forEach items="${assessmentList}" var="assessment" varStatus="status">
        <tr>
            <td width="10%">
                <text>${assessment.encounterObjectId}</text>
            </td>
            <td width="14%">
                <text>${assessment.encStartDateTime}</text>
            </td>
            <td width="10%">
                <text>${assessment.encType}</text>
            </td>
            <td width="10%">
                <text>${assessment.status}</text>
            </td>
            <td width="10%">
                <text>${assessment.temperature}</text>
            </td>
            <td width="10%">
                <text>${assessment.pulse}</text>
            </td>
            <td width="10%" style="white-space: nowrap">
                <text>${assessment.systolicBP} / ${assessment.dystolicBP}</text>
            </td>
            <td width="10%">
                <text>${assessment.height}</text>
            </td>
            <td width="10%">
                <text>${assessment.weight}</text>
            </td>
            <td width="6%">
                <form name="deleteAssessment${assessment.objectId}" method="POST" action="deleteAssessment">
                    <input type="hidden" name="objectId" value="${assessment.objectId}"/>
                    <a onclick="return confirm('Are you sure you want to delete this assessment?')"
                       href="javascript:document.deleteAssessment${assessment.objectId}.submit()">Delete</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>