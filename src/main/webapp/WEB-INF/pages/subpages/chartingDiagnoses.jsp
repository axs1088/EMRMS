<table id="diagnosisTable" border="1" align="center" cellpadding="5" width="100%">
    <tr>
        <th width="25%">
            <text>Code</text>
        </th>
        <th width="35%">
            <text>Description</text>
        </th>
        <th width="25%">
            <text>Type</text>
        </th>
        <th width="15%">
            <text>Action</text>
        </th>
    </tr>

    <c:set var="showAddActionVar" value="${showAddAction}"/>
    <c:if test="${showAddActionVar eq true}">
        <form:form id="addDiagnosisForm" method="post" action="addDiagnosis" modelAttribute="newDiagnosis"
                   name="addDiagnosisForm">
            <tr>
                <td width="25%">
                    <form:input path="code"/>
                </td>
                <td width="35%">
                    <form:input path="description"/>
                </td>
                <td width="25%">
                    <form:select path="priority">
                        <form:option value="1" label="Primary"/>
                        <form:option value="2" label="Secondary"/>
                    </form:select>
                </td>
                <td width="15%">
                    <a href="javascript:document.addDiagnosisForm.submit()">Add</a>
                </td>
            </tr>
        </form:form>
    </c:if>

    <c:forEach items="${diagnosisList}" var="diagnosis" varStatus="status">
        <tr>
            <td width="25%">
                <text>${diagnosis.code}</text>
            </td>
            <td width="35%">
                <text>${diagnosis.description}</text>
            </td>
            <td width="25%">
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
            <td width="15%">
                <form name="deleteDiagnosis${diagnosis.diagnosisObjectId}" method="POST" action="deleteDiagnosis">
                    <input type="hidden" name="diagnosisObjectId" value="${diagnosis.diagnosisObjectId}"/>
                    <a onclick="return confirm('Are you sure you want to delete this diagnosis?')"
                       href="javascript:document.deleteDiagnosis${diagnosis.diagnosisObjectId}.submit()">Delete</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>