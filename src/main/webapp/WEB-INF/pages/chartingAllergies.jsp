<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html>

<table id="allergyTable" border="1" align="center" cellpadding="5" width="100%">
    <tr>
        <th width="55%">
            <text>Allergy Name</text>
        </th>
        <th width="15%">
            <text>Severity</text>
        </th>
        <th width="15%">
            <text>Type</text>
        </th>
        <th width="15%">
            <text>Action</text>
        </th>
    </tr>

    <form:form id="addAllergyForm" method="post" action="addAllergy" modelAttribute="newAllergy" name="addAllergyForm">
        <tr>
            <td width="55%">
                <form:input path="allergyName"/>
            </td>
            <td width="15%">
                <form:select path="severity">
                    <form:option value="Severe"/>
                    <form:option value="High"/>
                    <form:option value="Medium"/>
                    <form:option value="Low"/>
                </form:select>
            </td>
            <td width="15%">
                <form:select path="allergyType">
                    <form:option value="1" label="Food"/>
                    <form:option value="2" label="Drug"/>
                    <form:option value="3" label="Other"/>
                </form:select>
            </td>
            <td width="15%">
                    <%--<form:button type="submit" value="Add" name="addForm">Add</form:button>--%>
                <a href="javascript:document.addAllergyForm.submit()">Add</a>
            </td>
        </tr>
    </form:form>

    <c:forEach items="${allergyList}" var="allergy" varStatus="status">
        <tr>
            <td width="55%">
                <text>${allergy.allergyName}</text>
            </td>
            <td width="15%">
                <text>${allergy.severity}</text>
            </td>
            <td width="15%">
                <text>
                    <c:choose>
                        <c:when test="${allergy.allergyType == '1'}">
                            Food
                        </c:when>
                        <c:when test="${allergy.allergyType == '2'}">
                            Drug
                        </c:when>
                        <c:when test="${allergy.allergyType == '3'}">
                            Other
                        </c:when>
                    </c:choose>
                </text>
            </td>
            <td width="15%">
                <form name="submitForm${allergy.allergyID}" method="POST" action="deleteAllergy">
                    <input type="hidden" name="allergyID" value="${allergy.allergyID}"/>
                    <a href="javascript:document.submitForm${allergy.allergyID}.submit()">Delete</a>
                </form>
                    <%--<input type="button" value="Edit"/>--%>
                    <%--<form:form id="deleteAllergyForm" method="post" action="deleteAllergy" modelAttribute="deletedAllergy">--%>
                    <%--<form:hidden path="allergyID">${allergy.allergyID}</form:hidden>--%>
                    <%--<form:button type="submit" value="Delete">Delete</form:button>--%>
                    <%--</form:form>--%>
            </td>
        </tr>
    </c:forEach>
</table>