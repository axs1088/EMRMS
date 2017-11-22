<table id="problemTable" border="1" align="center" cellpadding="5" width="100%" style="input{ width:auto; }">
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
        <th width="10%" title="Problem Code">
            <text>Code</text>
        </th>
        <th width="36%" title="Problem Description">
            <text>Description</text>
        </th>
        <th width="10%" title="Problem Type">
            <text>Type</text>
        </th>
        <th width="10" title="Add or remove a problem">
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
            <form:form id="addProblemForm" method="post" action="addProblem"
                       modelAttribute="newProblem" name="addProblemForm">
                <td width="10%">
                    <form:input size="8" path="code"/>
                </td>
                <td width="36%">
                    <form:input size="45" path="description"/>
                </td>
                <td width="10%">
                    <form:select path="priority">
                        <form:option value="1" label="Primary"/>
                        <form:option value="2" label="Secondary"/>
                    </form:select>
                </td>
                <td width="10%">
                    <a href="javascript:document.addProblemForm.submit()">Add</a>
                </td>
            </form:form>
        </tr>
    </c:if>

    <c:forEach items="${problemList}" var="problem" varStatus="status">
        <tr>
            <td width="10%">
                <text>${problem.encounterId}</text>
            </td>
            <td width="14%">
                <text>${problem.encStartDateTime}</text>
            </td>
            <td width="10%">
                <text>${problem.encType}</text>
            </td>
            <td width="10%">
                <text>${problem.code}</text>
            </td>
            <td width="36%">
                <text>${problem.description}</text>
            </td>
            <td width="10%">
                <text>
                    <c:choose>
                        <c:when test="${problem.priority == '1'}">
                            Primary
                        </c:when>
                        <c:when test="${problem.priority == '2'}">
                            Secondary
                        </c:when>
                    </c:choose>
                </text>
            </td>
            <td width="10%">
                <form name="deleteProblem${problem.objectId}" method="POST" action="deleteProblem">
                    <input type="hidden" name="objectId" value="${problem.objectId}"/>
                    <a onclick="return confirm('Are you sure you want to delete this problem?')"
                       href="javascript:document.deleteProblem${problem.objectId}.submit()">Delete</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>