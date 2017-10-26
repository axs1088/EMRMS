<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Note: this is already wrapped inside a <div> with proper styling -->
<table border="1" align="center" cellpadding="5" width="100%">
    <tr>
        <th width="70%">
            <text>Allergy Name</text>
        </th>
        <th width="15%">
            <text>Severity</text>
        </th>
        <th width="15%">
            <text>Type</text>
        </th>
    </tr>

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
        </tr>
    </c:forEach>
</table>