<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Patient Locator</title>
        <style>
			body {
				background-color: #E0F2F7;
			}
			table, th, td {
    			border-collapse: collapse;
    			padding: 15px;
    			text-align: left;
			}
        </style>
    </head>
    <body>
        <h>Patient Locator</h>
        <div style='float: right;'><a href="home.htm">Home</a></div>
        <form:form id="patientLocator" modelAttribute="patient" action="patientLocator" method="post">
            <table align="center">
                <tr>
                    <td>
                        <form:label path="lName">Last Name: </form:label>
                    </td>
                    <td>
                        <form:input path="lName" name="lName" id="lName" />
                    </td>
                </tr></tr>
                <tr>
                    <td>
                        <form:label path="fName">First Name:</form:label>
                    </td>
                    <td>
                        <form:input path="fName" name="fName" id="fName" />
                    </td>
                </tr></tr>
                <tr>
                    <td>
                        <form:label path="gender">Gender:</form:label>
                    </td>
                    <td>
                        <input type="radio" name="gender" value="1" checked> Male<br>
                        <input type="radio" name="gender" value="2"> Female<br>
                        <input type="radio" name="gender" value="3"> Other<br><br>
                    </td>
                </tr></tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <form:button id="findPatient" name="findPatient">Find Patient</form:button>
                    </td>
                </tr>
                <tr></tr>
            </table>
        </form:form>

        </br></br>
        <h>Patient Locator Results</h>
        <table cellpadding="10">
        		    <tr style="border-bottom:1pt solid black;border-top:1pt solid black;">
        		        <th>Name</th>
        		        <th>DOB</th>
        		        <th>Gender</th>
        		        <th>Location</th>
        		        <th>MRN</th>
        		        <th>Encounter</th>
        		    </tr>
        		    <c:forEach items="${hPatientList}" var="patient" varStatus="status">
        		        <tr>
        		            <td width="130">${patient.lastName}, ${patient.firstName}</td>
        		            <td width="100">${patient.birthdate}</td>
        		            <td width="100">
        			            <c:choose>
        				            <c:when test="${patient.gender == '1'}">
        							  Male
        							</c:when>
        							<c:otherwise>
        								Female
        							</c:otherwise>
        						</c:choose>
        					</td>
        		            <td width="100"></td>
        		            <td width="100">${patient.mPINo}</td>
        		            <td width="100">
        			            <c:choose>
        				            <c:when test="${patient.encStatus == '1'}">
        							  	Active
        							</c:when>
        							<c:otherwise>
        								Not Active
        							</c:otherwise>
        						</c:choose>
        					</td>
        		        </tr>
        		    </c:forEach>
        		</table>
    </body>
    </html>