<table id="diagnosisTable" border="1" align="center" cellpadding="5" width="100%">
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
        <th width="10%" title="Diagnosis code">
            <text>Code</text>
        </th>
        <th width="36%" title="Diagnosis description">
            <text>Description</text>
        </th>
        <th width="10%" title="Diagnosis severity">
            <text>Type</text>
        </th>
        <th width="10%" title="Add or remove a diagnosis">
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
            <form:form id="addDiagnosisForm" method="post" action="addDiagnosis" modelAttribute="newDiagnosis"
                       name="addDiagnosisForm">

                <td width="10%">
                    <form:input path="code"/>
                </td>
                <td width="36%">
                    <form:input path="description"/>
                </td>
                <td width="10%">
                    <form:select path="priority">
                        <form:option value="1" label="Primary"/>
                        <form:option value="2" label="Secondary"/>
                    </form:select>
                </td>
                <td width="10%">
                    <a href="javascript:document.addDiagnosisForm.submit()">Add</a>
                </td>
            </form:form>
        </tr>
    </c:if>

    <c:forEach items="${diagnosisList}" var="diagnosis" varStatus="status">
        <tr>
            <td width="10%">
                <text>${diagnosis.encounterId}</text>
            </td>
            <td width="14%">
                <text>${diagnosis.encStartDateTime}</text>
            </td>
            <td width="10%">
                <text>${diagnosis.encType}</text>
            </td>
            <td width="10%">
                <text>${diagnosis.code}</text>
            </td>
            <td width="36%">
                <text>${diagnosis.description}</text>
            </td>
            <td width="10%">
                <text>
                    <c:choose>
                        <c:when test="${diagnosis.priority == '1'}">
                            Primary
                        </c:when>
                        <c:when test="${diagnosis.priority == '2'}">
                            Secondary
                        </c:when>
                    </c:choose>
                </text>
            </td>
            <td width="10%">
                <form name="deleteDiagnosis${diagnosis.diagnosisObjectId}" method="POST" action="deleteDiagnosis">
                    <input type="hidden" name="diagnosisObjectId" value="${diagnosis.diagnosisObjectId}"/>
                    <a onclick="return confirm('Are you sure you want to delete this diagnosis?')"
                       href="javascript:document.deleteDiagnosis${diagnosis.diagnosisObjectId}.submit()">Delete</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>