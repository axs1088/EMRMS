<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome</title>
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
        <h>EMRMS</h>
        <div style='float: right;'><a href="home.htm">Home</a></div>   
        </br></br></br></br>
        <table cellpadding="10">
		    <tr style="border-bottom:1pt solid black;border-top:1pt solid black;">
		        <th>Name</th>
		        <th>DOB</th>
		        <th>Gender</th>
		        <th>Location</th>
		        <th>MRN</th>
		        <th>Encounter</th>
		    </tr>
		    <c:forEach items="${hCensusList}" var="physicianCensus" varStatus="status">
		        <tr>
		            <td width="130">${physicianCensus.lastName}, ${physicianCensus.firstName}</td>
		            <td width="100">${physicianCensus.birthdate}</td>
		            <td width="100">
			            <c:choose>
				            <c:when test="${physicianCensus.gender == '1'}">
							  Male
							</c:when>
							<c:otherwise>
								Female
							</c:otherwise>
						</c:choose>
					</td>
		            <td width="100"></td>
		            <td width="100">${physicianCensus.mPINo}</td>
		            <td width="100">
			            <c:choose>
				            <c:when test="${physicianCensus.encStatus == '1'}">
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